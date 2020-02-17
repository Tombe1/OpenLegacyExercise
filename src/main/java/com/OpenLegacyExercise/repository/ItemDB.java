package com.OpenLegacyExercise.repository;

import com.OpenLegacyExercise.Exceptions.ItemNotFoundException;
import com.OpenLegacyExercise.dto.Item;
import com.OpenLegacyExercise.Exceptions.ItemCantBeAddedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemDB {

    @Autowired
    private ItemRepo itemRepo;

    public List<Item> inventoryItemsList() {
        return (List<Item>) itemRepo.findAll();
    }

    public Item itemDetails(int id) throws ItemNotFoundException {
        Optional<Item> opt = itemRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ItemNotFoundException();
    }

    public int quantityByInventoryCode(int inventoryCode) {
        return itemRepo.getItemByInventoryCode(inventoryCode).getAmount();
    }

    public int quantityByName(String name) {
        return itemRepo.getItemByName(name).getAmount();
    }

    public boolean updateStockById(Item item) {
        if (itemRepo.existsById(item.getId())) {
            itemRepo.save(item);
            return true;
        }
        return false;
    }

    public void addItem(Item item) throws ItemCantBeAddedException {
        itemRepo.save(item);
    }

    public void deleteItem(int id) {
        itemRepo.deleteById(id);
    }


}
