package org.example.todo.service.search.impl;
import org.example.todo.dto.TaskDto;
import org.example.todo.entity.Task;
import org.example.todo.mapper.TaskMapper;
import org.example.todo.service.search.TaskSearchService;
import jakarta.transaction.Transactional;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service  // Ensure Spring can detect this implementation
public class TaskSearchServiceImpl implements TaskSearchService {

    private final EntityManager entityManager;
    private final TaskMapper mapper;

    public TaskSearchServiceImpl(EntityManager entityManager, TaskMapper mapper) {
        this.entityManager = entityManager;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public List<TaskDto> search(String text, int limit) {
        SearchSession searchSession = Search.session(entityManager);
        List<Task> hits = searchSession.search(Task.class)
                .where(f -> f.match()
                        .fields("title", "description")
                        .matching(text)
                        .fuzzy(2))
                .fetchHits(limit);
        return hits.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
