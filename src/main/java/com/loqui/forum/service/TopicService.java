package com.loqui.forum.service;

import com.loqui.forum.entity.Post;
import com.loqui.forum.entity.Topic;
import com.loqui.forum.entity.User;
import com.loqui.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public void save(Topic topic){
        topicRepository.save(topic);
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public Optional<Topic> findById(Long id) {
        return topicRepository.findById(id);
    }
}
