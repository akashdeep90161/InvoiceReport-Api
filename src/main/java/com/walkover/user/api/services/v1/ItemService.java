package com.walkover.user.api.services.v1;


import com.walkover.user.api.dao.model.Item;
import com.walkover.user.api.dao.repository.ItemRepository;
import com.walkover.user.api.exception.AlreadyExistException;
import com.walkover.user.api.exception.InvalidRequestException;
import com.walkover.user.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author Akash Deep Gupta
 */

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(rollbackFor = {Throwable.class})
    public Item saveItem(Item item)   {
        //  if(doesItemExist(item.getItemName()))
         // {
           //   throw new AlreadyExistException("Item with given name already exist");
          //}
        Item savedItem = itemRepository.save(item);
        return savedItem;
    }
    @Transactional(rollbackFor = {Throwable.class})
    public Item update(String oldItemName,long id,Item newItem) throws NotFoundException {

        if(!doesItemExist(oldItemName))
        {
            throw new NotFoundException("Item with given name does not exist.");
        }
        Item oldItem=itemRepository.findByItemNameAndUserId(oldItemName,id).get();
        oldItem.setItemName(newItem.getItemName());
        oldItem.setPrice(newItem.getPrice());
        itemRepository.save(oldItem);
        return oldItem;
    }

    @Transactional
    public void deleteItem(String itemName,long id) throws NotFoundException {
       if (!doesItemExist(itemName)) {
           throw new NotFoundException("Item with given name does not exist.");
      }
        Item item=itemRepository.findByItemNameAndUserId(itemName,id).get();
        itemRepository.delete(item);
    }
    @Transactional
    public Item getItem(String itemName,long id) throws NotFoundException {
        if (!doesItemExist(itemName)) {
            throw new NotFoundException("Item with given name does not exist.");
        }
        return itemRepository.findByItemNameAndUserId(itemName,id).get();
    }
    @Transactional
    public List<Item> getAllItem() throws NotFoundException {
        return (List<Item>) itemRepository.findAll();
    }
   public List<Item> getItemByUserId(long userId) throws NotFoundException
   {
       return  itemRepository.findByUserId(userId);
   }



   public String getTotalAmount(long userId) throws NotFoundException
   {
       List<Item> items= itemRepository.findByUserId(userId);
       if(items.size()==0)
       {
           throw new NotFoundException("Unable to genrate Invoice: Cart is empty");
       }
       double totalAmount=0;
       for(Item item:items)
       {
           totalAmount+= Double.parseDouble(item.getPrice());
       }
       return String.valueOf(totalAmount);
    }


    public boolean doesItemExist(String itemName ) {
        return itemRepository.doesItemExist(itemName);
    }

}
