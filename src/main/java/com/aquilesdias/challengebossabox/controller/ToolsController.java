package com.aquilesdias.challengebossabox.controller;

import com.aquilesdias.challengebossabox.domain.Tools;
import com.aquilesdias.challengebossabox.domain.dto.ToolsDTO;
import com.aquilesdias.challengebossabox.services.ToolsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "API TOOLS CONTROLLER")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/tools")
public class ToolsController {

    private final ToolsService service;

    @Operation(summary = "Create Tool")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Tool created") })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity save (@RequestBody ToolsDTO dto){
        Tools tools = service.saveTools(dto);
        return ResponseEntity.ok().body(tools);
    }

    @Operation(summary = "Filter by tag ")
    @GetMapping("filter")
    public ResponseEntity<List<Tools>> findByTag(@RequestParam("tag") String tag) {
        List<Tools> tools = service.findByTag(tag);
        return ResponseEntity.ok(tools);
    }

    @Operation(summary = "List tools")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of tools.")})
    @GetMapping
    public ResponseEntity<List<Tools>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Update tool by Id")
    @PutMapping("{id}")
    public ResponseEntity<Tools> updateTools(@PathVariable Long id, @RequestBody ToolsDTO dto){
        Tools tools = service.updateTools(id,dto);
        return ResponseEntity.ok().body(tools);
    }

    @Operation(summary = "Delete tool by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully deleted")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Tools> deleteTools(@PathVariable Long id){
        service.deleteTools(id);
        return ResponseEntity.ok().build();
    }
}
