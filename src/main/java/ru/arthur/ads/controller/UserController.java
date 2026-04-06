package ru.arthur.ads.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.arthur.ads.dto.UpdateUser;
import ru.arthur.ads.dto.User;
import ru.arthur.ads.service.UserService;
import ru.arthur.ads.dto.NewPassword;
import ru.arthur.ads.service.AuthService;
import org.springframework.web.multipart.MultipartFile;
import ru.arthur.ads.service.ImageService;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final ImageService imageService;


    @GetMapping("/me")
    public ResponseEntity<User> getUser(Authentication authentication) {
        return userService.findByEmail(authentication.getName())
                .map(userService::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/me")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUser updateUser,
                                           Authentication authentication) {
        return userService.findByEmail(authentication.getName())
                .flatMap(user -> userService.updateUser(user.getId(), updateUser))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword,
                                            Authentication authentication) {
        boolean changed = authService.changePassword(
                authentication.getName(),
                newPassword.getCurrentPassword(),
                newPassword.getNewPassword()
        );

        if (changed) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/me/image")
    public ResponseEntity<User> updateUserImage(@RequestParam("image") MultipartFile image,
                                                Authentication authentication) throws IOException {
        return userService.findByEmail(authentication.getName())
                .flatMap(user -> {
                    try {
                        String fileName = imageService.saveImage(image);
                        String imagePath = "/images/" + fileName;
                        return userService.updateUserImage(user.getId(), imagePath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}