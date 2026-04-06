package ru.arthur.ads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arthur.ads.entity.CommentEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    List<CommentEntity> findByAdId(Integer adId);
}