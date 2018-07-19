package com.walkover.user.api.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Table(name = "invoice")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice implements Serializable {

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceId;

  // @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL, optional = false)
    @NotNull
    @Column(name = "user_id")
    private long userId;

    @NotNull
    @Column(name = "total_amount")
    private String totalAmount;



    @OneToMany(mappedBy = "user")
   private List<Item> items = new ArrayList<>(0);



    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
       return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
