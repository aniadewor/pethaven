package com.pet.pethaven.response;

import com.pet.pethaven.model.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MenuResponse {
    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    List<Menu> menu;
}
