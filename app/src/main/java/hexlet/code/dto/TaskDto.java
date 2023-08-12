package hexlet.code.dto;

import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private static final int MIN_LENGTH = 1;

    @NotBlank(message = "Name should not be empty")
    @Size(min = MIN_LENGTH)
    private String name;

    private String description;

    @NotNull(message = "Task status should not be empty")
    private Long taskStatusId;

    @NotNull(message = "Author should not be empty")
    private Long authorId;

    private Long executorId;
}
