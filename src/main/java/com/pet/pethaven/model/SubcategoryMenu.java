package com.pet.pethaven.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SubcategoryMenu {
    private String subcategory;
    private String url;

    public SubcategoryMenu(String subcategory, String url) {
        this.subcategory = subcategory;
        this.url = url;
    }
}
