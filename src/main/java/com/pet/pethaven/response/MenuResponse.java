package com.pet.pethaven.response;

import com.pet.pethaven.model.Menu;
import com.pet.pethaven.model.SubcategoryMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MenuResponse {
    List<Menu> menu;
}
