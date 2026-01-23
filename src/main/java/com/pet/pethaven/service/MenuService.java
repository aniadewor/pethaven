package com.pet.pethaven.service;

import com.pet.pethaven.model.Menu;
import com.pet.pethaven.response.MenuResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {
    public MenuResponse buildMenu(){
        List<Menu> menu = new ArrayList<>();
        menu.add(new Menu("Karma dla kota","/cat/food"));
        menu.add(new Menu("Karma dla psa", "/dog/food"));

        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setMenu(menu);
        System.out.println(menuResponse.getMenu());
        return menuResponse;
    }
}
