package hexlet.code.service;

import hexlet.code.dto.TaskDto;
import hexlet.code.model.Task;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import hexlet.code.repository.TaskRepository;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final TaskStatusService statusService;

    @Override
    public Task createNewTask(TaskDto taskDto) {
        Task task = modificationTask(taskDto);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, TaskDto taskDto) {
        Task task = modificationTask(taskDto);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getReferenceById(id);
    }

    private Task modificationTask(TaskDto taskDto) {
        Task task = new Task();
        User author = userService.getUserById(taskDto.getAuthorId());
        User executor = userService.getUserById(taskDto.getExecutorId());
        TaskStatus status = statusService.getStatus(taskDto.getTaskStatusId());

        task.setName(taskDto.getName());
        task.setAuthor(author);
        task.setExecutor(executor);
        task.setDescription(taskDto.getDescription());
        task.setTaskStatus(status);

        return task;
    }
}
