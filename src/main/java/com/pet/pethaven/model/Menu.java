package com.pet.pethaven.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Menu {
    private String label;
    private String url;
    private List<Menu> items;

    public Menu(String label, String url) {
        this.label = label;
        this.url = url;
    }

}
