package com.OpenLegacyExercise.repository;

import com.OpenLegacyExercise.dto.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<Item , Integer> {
    Item getItemByInventoryCode(long inventoryCode);
    Item getItemByName(String name);
}
