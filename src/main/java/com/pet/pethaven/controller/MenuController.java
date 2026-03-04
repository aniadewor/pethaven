package com.pet.pethaven.controller;

import com.pet.pethaven.model.Menu;
import com.pet.pethaven.response.MenuResponse;
import com.pet.pethaven.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/menu/")
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/getMenu")
    public ResponseEntity<List<Menu>> getMenu() {
        MenuResponse menu = menuService.buildMenu();
        return new ResponseEntity<>(menu.getMenu(), HttpStatus.OK);
    }
}
