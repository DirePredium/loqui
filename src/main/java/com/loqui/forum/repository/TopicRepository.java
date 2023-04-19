package com.loqui.forum.repository;

import com.loqui.forum.entity.Topic;
import com.loqui.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByTitle(String title);

    List<Topic> findByTitleContaining(String title);
}
