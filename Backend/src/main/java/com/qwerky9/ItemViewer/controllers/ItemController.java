package com.qwerky9.ItemViewer.controllers;

import com.qwerky9.ItemViewer.entity.Item;
import com.qwerky9.ItemViewer.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @GetMapping("/paged")
    public Page<Item> getPagedItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        Pageable pageable = PageRequest.of(page,size);
        return itemService.getPagedItems(pageable);
    }

    // For fetching by ID (no pagination needed)
    @GetMapping("/by-id")
    public List<Item> getItemsByIdName(@RequestParam Integer idName) {
        return itemService.getItemsByIdName(idName);
    }

    // For fetching by time (no pagination, unless it grows large later)
    @GetMapping("/by-time")
    public List<Item> getItemsFromTime(@RequestParam String time) {
        return itemService.getItemsFromTime(time);
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        Item createdItem = itemService.addItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Item> updateItem(@RequestBody Item item){
        Item resultItem = itemService.updateItem(item);
        if (resultItem != null){
            return new ResponseEntity<>(resultItem, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idName}")
    public ResponseEntity<String> deleteItem(@PathVariable Integer idName){
        itemService.deleteItem(idName);
        return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
    }
}
