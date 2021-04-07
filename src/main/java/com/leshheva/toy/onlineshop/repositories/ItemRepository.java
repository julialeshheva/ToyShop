package com.leshheva.toy.onlineshop.repositories;

import com.leshheva.toy.onlineshop.entities.Item;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Long>, JpaSpecificationExecutor<Item> { //JpaRepository<Item, Long> {// должны указать тип объекта с которым собираемся работать
    Item findByTitle(String title);
}
