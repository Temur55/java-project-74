package hexlet.code.service;

import hexlet.code.dto.TaskDto;
import hexlet.code.model.Task;

import java.util.List;

public interface TaskService {
    Task createNewTask(TaskDto taskDto);
    Task updateTask(Long id, TaskDto taskDto);
    void deleteTask(Long id);
    List<Task> getAll();
    Task getTaskById(Long id);
}
