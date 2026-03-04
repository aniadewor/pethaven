package com.pet.pethaven.service;

import com.pet.pethaven.model.Menu;
import com.pet.pethaven.repository.MenuRepository;
import com.pet.pethaven.response.MenuResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {
    public MenuRepository menuRepository;
    public MenuResponse buildMenu(){
        List<Menu> itemsCat = new ArrayList<>();
        List<Menu> itemsDog = new ArrayList<>();
        List<Menu> menuList = new ArrayList<>();
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        itemsCat.add(new Menu("sucha", "/cat/food/dry"));
        itemsCat.add(new Menu("mokra", "/cat/food/wet"));
        itemsDog.add(new Menu("sucha", "/dog/food/dry"));
        itemsDog.add(new Menu("mokra", "/dog/food/wet"));
        createMenu(menu1, itemsCat, menu2, itemsDog, menuList);
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setMenu(menuList);
        return menuResponse;
    }

    private void createMenu(Menu menu1, List<Menu> itemsCat, Menu menu2, List<Menu> itemsDog, List<Menu> menuList) {
        menu1.setUrl("/cat/food/");
        menu1.setItems(itemsCat);
        menu2.setUrl("/dog/food/");
        menu2.setItems(itemsDog);
        menu1.setLabel("Karma dla kota");
        menuList.add(menu1);
        menuList.add(menu2);
        menuRepository.save(menu1);
        menuRepository.save(menu2);
    }
}
