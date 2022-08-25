package com.mihak.jumun.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String street;
    private String zipCodeAdr;
    private String detailAdr;

    protected Address() {
    }

    public Address(String street, String zipCodeAdr, String detailAdr) {
        this.street = street;
        this.zipCodeAdr = zipCodeAdr;
        this.detailAdr = detailAdr;
    }
}
