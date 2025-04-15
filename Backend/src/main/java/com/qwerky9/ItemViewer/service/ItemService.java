package com.qwerky9.ItemViewer.service;

import com.qwerky9.ItemViewer.entity.Item;
import com.qwerky9.ItemViewer.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Component
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public Page<Item> getPagedItems(Pageable pageable){
        return itemRepository.findAll(pageable);
    }

    public List<Item> getItemsFromTime(String time){
        return itemRepository.findAll().stream()
                .filter(item -> time.equals(item.getTime()))
                .collect(Collectors.toList());
    }

    public List<Item> getItemsByIdName(Integer idName){
        return itemRepository.findAll().stream()
                .filter(item -> item.getIdName().equals(idName))
                .collect(Collectors.toList());
    }

    public Item addItem(Item item){
        itemRepository.save(item);
        return item;
    }

    public Item updateItem(Item updatedItem){
        Optional<Item> existingItem = itemRepository.findByIdName(updatedItem.getIdName());

        if(existingItem.isPresent()){
            Item itemToUpdate = existingItem.get();
            itemToUpdate.setIdName(updatedItem.getIdName());
            itemToUpdate.setName(updatedItem.getName());
            itemToUpdate.setOption(updatedItem.getOption());
            itemToUpdate.setTime(updatedItem.getTime());
            itemToUpdate.setMeshfilename(updatedItem.getMeshfilename());
            itemToUpdate.setDesc(updatedItem.getDesc());

            itemRepository.save(itemToUpdate);
            return itemToUpdate;
        }
        return null;
    }

    @Transactional
    public void deleteItem(Integer idName){
        itemRepository.deleteByIdName(idName);
    }
}
