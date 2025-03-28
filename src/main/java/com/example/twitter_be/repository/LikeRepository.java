package com.example.twitter_be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.twitter_be.models.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
    @Query("SELECT l FROM Like l WHERE l.twit.id = :twit AND l.user.id = :user")
    public Like isLikeExists(@Param("twit") long twit, @Param("user") long user);

    @Query("SELECT l FROM Like l WHERE l.twit.id = :twitid")
    public List<Like> findByTwitId(@Param("twitid") long twit);
}
