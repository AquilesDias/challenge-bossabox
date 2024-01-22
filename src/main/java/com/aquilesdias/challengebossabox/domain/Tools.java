package com.aquilesdias.challengebossabox.domain;

import com.aquilesdias.challengebossabox.domain.dto.ToolsDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"title", "link", "description", "tags", "id"})
@Data
@Entity
public class Tools {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String link;
    private String description;
    private List<String> tags;

    public Tools(ToolsDTO dto){
        this.title       = dto.title();
        this.link        = dto.link() ;
        this.description = dto.description();
        this.tags        = dto.tags();
    }
}
