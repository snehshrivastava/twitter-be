package com.example.twitter_be.service;

import java.util.List;

import com.example.twitter_be.dtos.TwitReplyRequest;
import com.example.twitter_be.exceptions.UserException;
import com.example.twitter_be.models.Twit;
import com.example.twitter_be.models.User;

public interface TwitService {
    
    public Twit createTwit(Twit twit, User user) throws UserException ;
    public List<Twit> findAllTwit();
    public Twit reTwit(Twit twit, User user) throws UserException;

    public Twit findByTwitId(long id);

    public void deleteTwit(long twitid, long userid) throws UserException;

    public Twit removeFromReTwit(Twit twit, User user) throws UserException;

    public Twit createdReplyTwit(TwitReplyRequest twit, User user) throws UserException;

    public List<Twit> getUserTwits(User user);

    public List<Twit> findByLikesContainsUser(User user);
}
