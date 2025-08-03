package org.example.todo.service;

import org.example.todo.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    TaskDto create(TaskDto dto);
    TaskDto update(Long id, TaskDto dto);
    TaskDto getById(Long id);
    Page<TaskDto> getAll(Pageable pageable);
    void delete(Long id);
}
