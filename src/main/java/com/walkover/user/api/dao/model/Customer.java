package com.walkover.user.api.dao.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
@Table(name = "customer")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @NotNull
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @NotNull
    @Column(name = "customer_name")
    private String itemName;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @NotNull
    @Column(name = "customer_items")
    private Map<Item,Integer> items = new HashMap<>(0);

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @NotNull
    @Column(name = "customer_items")
    private Set<Item> items = new HashSet<>(0);

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


} */
