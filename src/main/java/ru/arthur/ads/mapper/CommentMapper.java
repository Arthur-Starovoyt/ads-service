package ru.arthur.ads.mapper;

import org.springframework.stereotype.Component;
import ru.arthur.ads.dto.Comment;
import ru.arthur.ads.dto.CreateOrUpdateComment;
import ru.arthur.ads.entity.AdEntity;
import ru.arthur.ads.entity.CommentEntity;
import ru.arthur.ads.entity.UserEntity;

@Component
public class CommentMapper {

    public Comment toDto(CommentEntity entity) {
        if (entity == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setPk(entity.getId());
        comment.setAuthor(entity.getAuthor().getId());
        comment.setAuthorFirstName(entity.getAuthor().getFirstName());
        comment.setAuthorImage(entity.getAuthor().getImage());
        comment.setCreatedAt(entity.getCreatedAt());
        comment.setText(entity.getText());
        return comment;
    }

    public CommentEntity fromDto(CreateOrUpdateComment dto, UserEntity author, AdEntity ad) {
        if (dto == null || author == null || ad == null) {
            return null;
        }

        CommentEntity entity = new CommentEntity();
        entity.setText(dto.getText());
        entity.setCreatedAt(System.currentTimeMillis());
        entity.setAuthor(author);
        entity.setAd(ad);
        return entity;
    }

    public void updateCommentFields(CreateOrUpdateComment dto, CommentEntity entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setText(dto.getText());
    }
}