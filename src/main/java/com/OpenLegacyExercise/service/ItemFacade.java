package com.OpenLegacyExercise.service;

import com.OpenLegacyExercise.Exceptions.ItemCantBeAddedException;
import com.OpenLegacyExercise.Exceptions.ItemExistsException;
import com.OpenLegacyExercise.Exceptions.ItemNotFoundException;
import com.OpenLegacyExercise.dto.Item;
import com.OpenLegacyExercise.repository.ItemDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class ItemFacade {

    @Autowired
    protected ItemDB itemDB;

    public List<Item> inventoryItemsList() {
        return itemDB.inventoryItemsList();
    }

    public Item itemDetails(int id) throws ItemNotFoundException {
        return itemDB.itemDetails(id);
    }

    public int quantityByInventoryCode(int inventoryCode) {
        return itemDB.quantityByInventoryCode(inventoryCode);
    }

    public int quantityByName(String name) {
        return itemDB.quantityByName(name);
    }

    public void updateStockById(Item item) throws ItemExistsException {
        List<Item> allItems = itemDB.inventoryItemsList();
        for (Item i : allItems) {
            if (!item.getName().equals(i.getName()) || item.getId() != i.getId()) {
                throw new ItemExistsException();

            } else continue;

        }
        itemDB.updateStockById(item);

    }

    public void addItem(Item item) throws ItemCantBeAddedException {
        List<Item> addItem = itemDB.inventoryItemsList();
        for (Item i : addItem){
            if (item.getId() == i.getId()){
                if (item.getInventoryCode() == i.getInventoryCode()){
                    throw new ItemCantBeAddedException();
                }
                break;
            }
        }
        itemDB.updateStockById(item);
        itemDB.addItem(item);
    }

    public void deleteItem(int id) throws ItemNotFoundException {
        itemDB.deleteItem(id);
    }
}
