package org.example.todo.controller;

import org.example.todo.dto.TaskDto;
import org.example.todo.service.TaskService;
import org.example.todo.service.search.TaskSearchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;
    private final TaskSearchService searchService;

    @Autowired
    public TaskController(TaskService service, TaskSearchService searchService) {
        this.service = service;
        this.searchService = searchService;
    }

    @PostMapping
    public TaskDto createTask(@Valid @RequestBody TaskDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public Page<TaskDto> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getAll(pageable);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<TaskDto> searchTasks(
            @RequestParam String query,
            @RequestParam(defaultValue = "10") int limit) {
        return searchService.search(query, limit);
    }
}
