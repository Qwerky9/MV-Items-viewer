package com.qwerky9.ItemViewer.repository;

import com.qwerky9.ItemViewer.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    void deleteByIdName(Integer idName);
    Optional<Item> findByIdName(Integer idName);
    Page<Item> findAll(Pageable pageable);

}
