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
        List<Menu> itemsCat = new ArrayList<>();
        itemsCat.add(new Menu("sucha", "/cat/food/dry"));
        itemsCat.add(new Menu("mokra", "/cat/food/wet"));
        List<Menu> itemsDog = new ArrayList<>();
        itemsDog.add(new Menu("sucha", "/dog/food/dry"));
        itemsDog.add(new Menu("mokra", "/dog/food/wet"));
        menu.add(new Menu("Karma dla kota","/cat/food",itemsCat));
        menu.add(new Menu("Karma dla psa", "/dog/food",itemsDog));

        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setMenu(menu);
        System.out.println(menuResponse.getMenu());
        return menuResponse;
    }
}
