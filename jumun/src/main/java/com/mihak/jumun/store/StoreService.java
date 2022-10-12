package com.mihak.jumun.store;

import com.mihak.jumun.entity.Address;
import com.mihak.jumun.entity.Owner;
import com.mihak.jumun.entity.Store;
import com.mihak.jumun.store.dto.CreateFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store save(CreateFormDto createFormDto, Owner owner) {
        Address address = new Address(createFormDto.getStreetAdr(), createFormDto.getZipCode(), createFormDto.getDetailAdr());

        Store store = Store.builder()
                .name(createFormDto.getName())
                .owner(owner)
                .address(address)
                .serialNumber(UUID.randomUUID().toString())
                .build();

        return storeRepository.save(store);
    }

    public Store findBySerialNumber(String serialNumber) {
        return storeRepository.findBySerialNumber(serialNumber);
    }

    public Optional<Store> findByOwner(Owner owner) {
        return storeRepository.findByOwner(owner);
    }
}
