package com.example.diary.domain.likes.repository;

import com.example.diary.domain.likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {

}
