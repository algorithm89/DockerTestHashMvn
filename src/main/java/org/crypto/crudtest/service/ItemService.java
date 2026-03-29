package org.crypto.crudtest.service;

import lombok.RequiredArgsConstructor;
import org.crypto.crudtest.dto.ItemRequest;
import org.crypto.crudtest.dto.ItemResponse;
import org.crypto.crudtest.exception.ItemNotFoundException;
import org.crypto.crudtest.model.Item;
import org.crypto.crudtest.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemResponse> findAll() {
        return itemRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ItemResponse findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        return toResponse(item);
    }

    public ItemResponse create(ItemRequest request) {
        Item item = Item.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return toResponse(itemRepository.save(item));
    }

    public ItemResponse update(Long id, ItemRequest request) {
        Item existing = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        return toResponse(itemRepository.save(existing));
    }

    public void delete(Long id) {
        Item existing = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        itemRepository.delete(existing);
    }

    private ItemResponse toResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }
}
