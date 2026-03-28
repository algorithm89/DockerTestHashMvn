package org.crypto.crudtest.service;

import lombok.RequiredArgsConstructor;
import org.crypto.crudtest.model.Item;
import org.crypto.crudtest.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Item update(Long id, Item updated) {
        Item existing = findById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        return itemRepository.save(existing);
    }

    public void delete(Long id) {
        Item existing = findById(id);
        itemRepository.delete(existing);
    }
}

