package com.kropotov.asrd.services.springdatajpa.titles;

import com.kropotov.asrd.entities.titles.Topic;
import com.kropotov.asrd.repositories.titles.TopicRepository;
import com.kropotov.asrd.services.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService implements TitleService<Topic, Long> {

    private final TopicRepository topicRepository;

    @Override
    public List<Topic> getAll() {
        List<Topic> list = new ArrayList<>();
        topicRepository.findAll().forEach(list::add);
        return list;
    }

    public Optional<Topic> getById(Long id) {
        return topicRepository.findById(id);
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic getByTitle(String title) {
        return topicRepository.findByTitle(title);
    }

    @Override
    public void deleteById(Long id) {
        topicRepository.deleteById(id);
    }
}
