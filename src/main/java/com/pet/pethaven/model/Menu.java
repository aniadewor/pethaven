package com.pet.pethaven.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
        private String category;
        private String url;
        private List<SubcategoryMenu> subcategory;

        public Menu(String category, String url) {
                this.category = category;
                this.url = url;
        }
}
