package com.loqui.forum.controller;

import com.loqui.forum.entity.Post;
import com.loqui.forum.entity.Topic;
import com.loqui.forum.entity.User;
import com.loqui.forum.service.PostService;
import com.loqui.forum.service.TopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public String index(Model model){
        List<Topic> topics = topicService.findAll();
        model.addAttribute("topics", topics);
        return "topics/topics";
    }

    @GetMapping("/{id}")
    public String showTopicById(Model model, @PathVariable("id") Long id){
        Optional<Topic> optionalTopic = topicService.findById(id);

        if(optionalTopic.isEmpty()) {
            model.addAttribute("errorMessage", "Can`t define topic");
            return "error";
        }
        model.addAttribute("topic", optionalTopic.get());
        return "topics/topic";
    }

}
