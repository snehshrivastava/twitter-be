package com.example.twitter_be.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.twitter_be.dtos.TwitReplyRequest;
import com.example.twitter_be.exceptions.UserException;
import com.example.twitter_be.models.Twit;
import com.example.twitter_be.models.User;
import com.example.twitter_be.repository.TwitRepository;

@Service
public class TwitServiceImpl implements TwitService {

    @Autowired
    TwitRepository twitRepository;



    @Override
    public Twit createTwit(Twit twit, User user) throws UserException {
        Twit twit1 = new Twit();
        twit1.setContent(twit.getContent());
        twit1.setCreatedAt(LocalDateTime.now());
        twit1.setUser(user);
        twit1.setReply(false);
        twit1.setRetwit(false);
        twitRepository.save(twit1);
        return twit1;
    }

    @Override
    public List<Twit> findAllTwit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllTwit'");
    }

    @Override
    public Twit reTwit(Twit twit, User user) throws UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reTwit'");
    }

    @Override
    public Twit findByTwitId(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTwitId'");
    }

    @Override
    public void deleteTwit(long twitid, long userid) throws UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTwit'");
    }

    @Override
    public Twit removeFromReTwit(Twit twit, User user) throws UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromReTwit'");
    }

    @Override
    public Twit createdReplyTwit(TwitReplyRequest twit, User user) throws UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createdReplyTwit'");
    }

    @Override
    public List<Twit> getUserTwits(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserTwits'");
    }

    @Override
    public List<Twit> findByLikesContainsUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByLikesContainsUser'");
    }
    
}
