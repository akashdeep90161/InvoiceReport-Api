package com.walkover.user.api.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


@Table(name = "items")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {
    @Id
    @NotNull
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @NotNull
    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "invoice_id")
   // private Invoice invoice;

  //  @JsonIgnore
   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "invoicce_id")
  //  private Invoice invoice;

    @NotNull
    @Pattern(regexp = "[0-9][0-9]*")
    @Column(name = "item_price")
    private String price;

  /*  @NotNull
    @Column(name = "quantity")
    private int  quantity; */


    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public User getUser() {
       return user;
   }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
     }

   /* public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    } */

}
