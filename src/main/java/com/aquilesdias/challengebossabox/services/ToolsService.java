package com.aquilesdias.challengebossabox.services;

import com.aquilesdias.challengebossabox.domain.dto.ToolsDTO;
import com.aquilesdias.challengebossabox.domain.Tools;
import com.aquilesdias.challengebossabox.repositories.ToolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolsService {

    @Autowired
    private ToolsRepository repository;

    public Tools saveTools(ToolsDTO dto){
        Tools newTols = new Tools(dto);
        return repository.save(newTols);
    }

    public List<Tools> findAll() {
        return repository.findAll();
    }

    public List<Tools> findByTag(String tag) {
        return repository.findAll().stream()
                .filter(tool -> tool.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    public Tools updateTools(Long id, ToolsDTO dto){
        Tools tools = repository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tools not found"));

        tools.setDescription(dto.description());
        tools.setLink(dto.link());
        tools.setTitle(dto.title());

        return repository.save(tools);

    }

    public void deleteTools(Long id){
        Tools tools = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tool with id not found"));
        repository.delete(tools);
    }

}
