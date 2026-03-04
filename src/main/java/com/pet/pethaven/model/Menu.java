package com.pet.pethaven.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder(value ={"label","url","items"})
@Document(collection = "menu")
public class Menu {
    @Id
    private String id;
    private String label;
    private String url;
    private List<Menu> items;

    public Menu(String label, String url) {
        this.label = label;
        this.url = url;
    }

}
