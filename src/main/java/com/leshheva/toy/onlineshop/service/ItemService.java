package com.leshheva.toy.onlineshop.service;

import com.leshheva.toy.onlineshop.entities.Item;
import com.leshheva.toy.onlineshop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Page<Item> getItemsWithPagingANDFiltering(Specification<Item> specification, Pageable pegeable){


      return itemRepository.findAll(specification, pegeable);

    }
}
