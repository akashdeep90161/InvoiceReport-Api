package com.walkover.user.api.dao.repository;


import com.walkover.user.api.dao.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

/**
 * @author Akash Deep Gupta
 */

public interface ItemRepository extends CrudRepository<Item,Long> {

   // @Query(value = "select items where (item_name=itemName AND user_id=id);")
    public Optional<Item> findByItemNameAndUserId(String itemName,long id);
    public List<Item> findByUserId(long id);


    @Query(value = "select case when count(i.item_id) > 0 then true else false end as item_name_exists from items i where i.item_name = :itemName",
            nativeQuery = true)
    public boolean doesItemExist(@Param("itemName") String itemName);

}
