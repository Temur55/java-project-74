package hexlet.code.controllers;

import hexlet.code.dto.LabelDto;
import hexlet.code.model.Label;
import hexlet.code.model.Task;
import hexlet.code.repository.LabelRepository;
import hexlet.code.service.LabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hexlet.code.controllers.LabelController.LABEL_PATH;
import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor
@RestController
@RequestMapping("${base-url}" + LABEL_PATH)
public class LabelController {
    public static final String LABEL_PATH = "/labels";
    public static final String ID = "/{id}";
    @Autowired
    private final LabelService labelService;
    @Autowired
    private final LabelRepository labelRepository;

    @Operation(summary = "Create new label")
    @ApiResponse(responseCode = "201", description = "Label created")
    @PostMapping
    @ResponseStatus(CREATED)
    public Label createNew(@RequestBody @Valid final LabelDto LabelDto) {
        return labelService.createNewLabel(LabelDto);
    }

    @Operation(summary = "Get all labels")
    @ApiResponses(@ApiResponse(responseCode = "200", content =
    @Content(schema = @Schema(implementation = Label.class))
    ))
    @GetMapping
    public List<Label> getAll() {
        return labelService.getAll();
    }

    @Operation(summary = "Find label by Id")
    @ApiResponses(@ApiResponse(responseCode = "200"))
    @GetMapping(ID)
    public Label getLabelById(@PathVariable final Long id) {
        return labelService.getLabelById(id);
    }

    @Operation(summary = "Update a label")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Label updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Label.class))}),
            @ApiResponse(responseCode = "404", description = "Label with that id not found")
    })
    @PutMapping(ID)
    public Label update(@PathVariable final long id, @RequestBody final LabelDto LabelDto) {
        return labelService.updateLabel(id, LabelDto);
    }

    @Operation(summary = "Delete a label")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Label deleted"),
            @ApiResponse(responseCode = "404", description = "Label with that id not found")
    })
    @DeleteMapping(ID)
    public void delete(@PathVariable final long id) {
        labelService.deletelabel(id);
    }

}