package org.example.todo.service.impl;

import org.example.todo.dto.TaskDto;
import org.example.todo.entity.Task;
import org.example.todo.exception.ResourceNotFoundException;
import org.example.todo.mapper.TaskMapper;
import org.example.todo.repository.TaskRepository;
import org.example.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repo;
    private final TaskMapper mapper;

    @Autowired
    public TaskServiceImpl(TaskRepository repo, TaskMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public TaskDto create(TaskDto dto) {
        Task task = mapper.toEntity(dto);
        return mapper.toDto(repo.save(task));
    }

    @Override
    public TaskDto update(Long id, TaskDto dto) {
        Task existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setDueDate(dto.getDueDate());
        existing.setStatus(dto.getStatus());
        return mapper.toDto(repo.save(existing));
    }

    @Override
    public TaskDto getById(Long id) {
        Task task = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        return mapper.toDto(task);
    }

    @Override
    public Page<TaskDto> getAll(Pageable pageable) {
        return repo.findAll(pageable)
                .map(mapper::toDto);
    }

    @Override
    public void delete(Long id) {
        Task task = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        repo.delete(task);
    }
}