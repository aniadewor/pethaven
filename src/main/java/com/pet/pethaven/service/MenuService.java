package com.pet.pethaven.service;

import com.pet.pethaven.model.Menu;
import com.pet.pethaven.model.SubcategoryMenu;
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

    public static final String CATHEGORY_CAT = "Kot";
    public static final String SUBCATHEGORY_CAT_DRY = "sucha karma";
    public static final String SUBCATHEGORY_CAT_WET = "mokra karma";
    public static final String SUBCATHEGORY_CAT_TREATS = "przysmaki";
    public static final String SUBCATHEGORY_CAT_LITTER_BOX = "kuweta dla kota";
    public static final String SUBCATHEGORY_CAT_LITTER = "żwirek";
    public static final String SUBCATHEGORY_CAT_SCRATCHING_POST = "drapak";
    public static final String SUBCATHEGORY_CAT_ACCESSORIES = "akcesoria";
    public static final String SUBCATHEGORY_CAT_CARE = "pielęgnacja";
    public static final String SUBCATHEGORY_CAT_TOYS = "zabawki";

    public static final String CATHEGORY_DOG = "Pies";
    public static final String SUBCATHEGORY_DOG_DRY = "sucha karma";
    public static final String SUBCATHEGORY_DOG_WET = "mokra karma";
    public static final String SUBCATHEGORY_DOG_TREATS = "przysmaki";
    public static final String SUBCATHEGORY_DOG_WALK = "spacer";
    public static final String SUBCATHEGORY_DOG_ACCESSORIES = "akcesoria";
    public static final String SUBCATHEGORY_DOG_CARE = "pielęgnacja";
    public static final String SUBCATHEGORY_DOG_TOYS = "zabawki";

    public MenuResponse buildMenu(){
        List<SubcategoryMenu> itemsCat = new ArrayList<>();
        List<SubcategoryMenu> itemsDog = new ArrayList<>();
        List<Menu> menuList = new ArrayList<>();
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        itemsCat.add(new SubcategoryMenu(SUBCATHEGORY_CAT_DRY, "/cat/food/dry"));
        itemsCat.add(new SubcategoryMenu(SUBCATHEGORY_CAT_WET, "/cat/food/wet"));
        itemsCat.add(new SubcategoryMenu(SUBCATHEGORY_CAT_TREATS, "/cat/food/treats"));
        itemsCat.add(new SubcategoryMenu(SUBCATHEGORY_CAT_LITTER_BOX, "/cat/food/litterbox"));
        itemsCat.add(new SubcategoryMenu(SUBCATHEGORY_CAT_LITTER, "/cat/food/litter"));
        itemsCat.add(new SubcategoryMenu(SUBCATHEGORY_CAT_SCRATCHING_POST, "/cat/food/scratchingpost"));
        itemsCat.add(new SubcategoryMenu(SUBCATHEGORY_CAT_ACCESSORIES, "/cat/food/accessories"));
        itemsCat.add(new SubcategoryMenu(SUBCATHEGORY_CAT_CARE, "/cat/food/care"));
        itemsCat.add(new SubcategoryMenu(SUBCATHEGORY_CAT_TOYS, "/cat/food/toys"));

        itemsDog.add(new SubcategoryMenu(SUBCATHEGORY_DOG_DRY, "/dog/food/dry"));
        itemsDog.add(new SubcategoryMenu(SUBCATHEGORY_DOG_WET, "/dog/food/wet"));
        itemsDog.add(new SubcategoryMenu(SUBCATHEGORY_DOG_TREATS, "/dog/food/treats"));
        itemsDog.add(new SubcategoryMenu(SUBCATHEGORY_DOG_WALK, "/dog/food/walk"));
        itemsDog.add(new SubcategoryMenu(SUBCATHEGORY_DOG_ACCESSORIES, "/dog/food/accessories"));
        itemsDog.add(new SubcategoryMenu(SUBCATHEGORY_DOG_CARE, "/dog/food/care"));
        itemsDog.add(new SubcategoryMenu(SUBCATHEGORY_DOG_TOYS, "/dog/food/toys"));

        createMenu(menu1, itemsCat, menu2, itemsDog, menuList);
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setMenu(menuList);
        return menuResponse;
    }

    private void createMenu(Menu menu1, List<SubcategoryMenu> subcategoryCats, Menu menu2, List<SubcategoryMenu> subcategoryDogs, List<Menu> menuList) {
        menu1.setUrl("/cat/food/");
        menu1.setSubcategory(subcategoryCats);
        menu2.setUrl("/dog/food/");
        menu2.setSubcategory(subcategoryDogs);
        menu1.setCategory(CATHEGORY_CAT);
        menu2.setCategory(CATHEGORY_DOG);
        menuList.add(menu1);
        menuList.add(menu2);
        menuRepository.save(menu1);
        menuRepository.save(menu2);
    }
}
