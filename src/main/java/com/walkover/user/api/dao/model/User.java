/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walkover.user.api.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Akash Deep Gupta
 */

@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  implements Serializable {

    @Id
    @NotNull
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "email_id")
    @Email(regexp = "[a-zA-Z0-9][a-zA-Z0-9]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
    private String emailId;

    @Size(min = 1, message = "User's name must have at least 1 character.")
    @Column(name = "name")
    private String name;

  /*  @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice; */


//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//  private Set<Item> items = new HashSet<>(0);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String eid) {
        this.emailId = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*
   public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    } */

    @Override
    public String toString() {
        return "id=" + emailId + ", name=" + name;
    }

}
