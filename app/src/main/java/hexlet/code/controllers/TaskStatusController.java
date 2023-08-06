package hexlet.code.controllers;


import hexlet.code.dto.TaskStatusDto;
import hexlet.code.model.TaskStatus;
import hexlet.code.service.TaskStatusService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${base-url}" + "/statuses")
@AllArgsConstructor
public class TaskStatusController {
    @Autowired
    private final TaskStatusService statusService;

    @GetMapping("/{id}")
    public TaskStatus getStatus(@PathVariable("id") Long id) {
        return statusService.getStatus(id);
    }

    @GetMapping("")
    public List<TaskStatus> getStatuses() {
        return statusService.getStatuses();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskStatus createStatus(@RequestBody @Valid TaskStatusDto taskStatusDto) {
        return statusService.createStatus(taskStatusDto);
    }

    @PutMapping("/{id}")
    public TaskStatus updateStatus(@RequestBody @Valid TaskStatusDto statusDto, @PathVariable Long id) {
        return statusService.updateStatus(statusDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteStatus(@PathVariable("id") Long id) {
        statusService.deleteStatus(id);
    }
}
