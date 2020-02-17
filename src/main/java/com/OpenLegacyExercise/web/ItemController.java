package com.OpenLegacyExercise.web;

import com.OpenLegacyExercise.Exceptions.ItemExistsException;
import com.OpenLegacyExercise.Exceptions.ItemNotFoundException;
import com.OpenLegacyExercise.dto.Item;
import com.OpenLegacyExercise.Exceptions.ItemCantBeAddedException;
import com.OpenLegacyExercise.service.ItemFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(value = "/item")
//@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

    @Autowired
    ItemFacade itemFacade;

    @GetMapping("allItemsInfo")
    public ResponseEntity<Object> inventoryItemsList() {
        try {
            return ResponseEntity.ok(itemFacade.inventoryItemsList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("itemDetails/{id}")
    public ResponseEntity<Object> itemDetails(@PathVariable int id) {
        try {
            return ResponseEntity.ok(itemFacade.itemDetails(id));
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("quantity/code/{inventoryCode}")
    public ResponseEntity<Object> quantityByInventoryCode(@PathVariable int inventoryCode) {
        try {
            return ResponseEntity.ok(itemFacade.quantityByInventoryCode(inventoryCode));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("quantity/name/{name}")
    public ResponseEntity<Object> quantityByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(itemFacade.quantityByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("item/update")
    public ResponseEntity<Object> updateStockById(@RequestBody Item item) throws ItemExistsException {
        try {
            itemFacade.updateStockById(item);
            return ResponseEntity.ok(item);
        } catch (ItemExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("item/add")
    public ResponseEntity<Object> addItem(@RequestBody Item item) {
        try {
            System.out.println("stage 1 " + item.toString());
            itemFacade.addItem(item);
            System.out.println("stage 2 " + item);
            return ResponseEntity.ok(item);
        } catch (ItemCantBeAddedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("item/delete/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable int id) {
        try {
            itemFacade.deleteItem(id);
            return ResponseEntity.ok().build();
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
