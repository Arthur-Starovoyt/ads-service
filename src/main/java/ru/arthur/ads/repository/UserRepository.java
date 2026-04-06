package ru.arthur.ads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arthur.ads.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);
}