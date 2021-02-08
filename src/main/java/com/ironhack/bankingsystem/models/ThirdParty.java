package com.ironhack.bankingsystem.models;

import javax.persistence.*;

@Entity
@Table(name = "third_party")
public class ThirdParty extends User {
    @PrimaryKeyJoinColumn(name = "id")
    private String name;
    private String hashKey;

    public ThirdParty(String name, String hashKey) {
        super(name, hashKey);
    }

    public ThirdParty() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
