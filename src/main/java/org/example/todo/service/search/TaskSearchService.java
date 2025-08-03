package org.example.todo.service.search;

import org.example.todo.dto.TaskDto;
import java.util.List;

public interface TaskSearchService {
    List<TaskDto> search(String text, int limit);
}
