package com.mihak.jumun.menu;

import com.mihak.jumun.entity.Address;
import com.mihak.jumun.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public void makeNewStore(Long storeId, String name, Address address) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (store.isEmpty()) {
            Store newStore = Store.createStore(name, address);
            storeRepository.save(newStore);
        }
    }
}
