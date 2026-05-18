package com.pet.pethaven.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@JsonPropertyOrder({"label", "url", "items"})
@Document(collection = "menu")
public record Menu(
        @Id
        String id,
        String label,
        String url,
        List<Menu> items
) {
        public Menu (String id, String label) {
                this(id,label,null,null);
        }
        public Menu()
        {this(null,"","",null);}

        public Menu createMenu(String label, String url, List<Menu> items){
                return new Menu(this.id, label, url, items);
        }
}
