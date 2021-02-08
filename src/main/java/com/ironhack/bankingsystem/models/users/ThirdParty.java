package com.ironhack.bankingsystem.models.users;

import javax.persistence.*;

@Entity
@Table(name = "third_party")
public class ThirdParty extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String hashKey;

    public ThirdParty(String name, String hashKey) {
        super(name, hashKey);
    }

    public ThirdParty() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
