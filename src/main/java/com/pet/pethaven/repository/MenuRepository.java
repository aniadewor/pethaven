package com.pet.pethaven.repository;

import com.pet.pethaven.model.Menu;
import com.pet.pethaven.model.SubcategoryMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends MongoRepository<Menu, Integer> {
}
