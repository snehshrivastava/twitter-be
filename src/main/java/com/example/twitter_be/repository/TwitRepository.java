package com.example.twitter_be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.twitter_be.models.Twit;
import com.example.twitter_be.models.User;


public interface TwitRepository extends JpaRepository<Twit, Long> {
    


    public List<Twit> findAllByIsRetwitOrderByCreatedAtDesc(boolean isRetwit);
    

    public List<Twit> findByRetwitUsersContainsOrUser_IdOrderByCreatedAtDesc(User user, long userid);

    public List<Twit> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("SELECT t FROM Twit t join t.likes l WHERE t.user.id = l.user.id and l.user.id = :userId")
    public List<Twit> findByLikesUser_id(long userId);
}
