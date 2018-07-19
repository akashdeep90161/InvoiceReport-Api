package com.walkover.user.api.controller;


import com.walkover.user.api.dao.model.Invoice;
import com.walkover.user.api.dao.model.Item;
import com.walkover.user.api.dao.model.User;
import com.walkover.user.api.exception.AlreadyExistException;
import com.walkover.user.api.exception.InvalidRequestException;
import com.walkover.user.api.exception.NotFoundException;
import com.walkover.user.api.models.commens.ApiResponse;
import com.walkover.user.api.models.commens.ApiStatus;
import com.walkover.user.api.resources.v1.ItemResource;
import com.walkover.user.api.resources.v1.UserResource;
import com.walkover.user.api.services.v1.ItemService;
import com.walkover.user.api.services.v1.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


/**
  @author Akash Deep Gupta
 */

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createUser(@RequestBody ItemResource itemResource, HttpServletRequest request)  throws AlreadyExistException {
        System.out.println("value of itemName:" + itemResource.getModel().getItemName());
        Item item = itemService.saveItem(itemResource.getModel());
        return new ResponseEntity<>(new ApiResponse(new ItemResource(item), ApiStatus.success), HttpStatus.CREATED);
    }

    @RequestMapping(method = PUT,value = "/{itemName}/{id}")
    public ResponseEntity<ApiResponse> updateItem(@PathVariable String itemName,@PathVariable long id, @RequestBody ItemResource itemResource, HttpServletRequest request) throws NotFoundException {
        Item item= itemService.update(itemName,id,itemResource.getModel());
        return new ResponseEntity<>(new ApiResponse(item, ApiStatus.success), HttpStatus.OK);
    }

    @RequestMapping(method = GET, value = "/{itemName}/{id}")
    public ResponseEntity<ApiResponse> getItem(@PathVariable String itemName,@PathVariable long id, HttpServletRequest request) throws Exception {
        Item item = itemService.getItem(itemName,id);
        return new ResponseEntity<>(new ApiResponse(new ItemResource(item), ApiStatus.success), HttpStatus.OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<ApiResponse> getAllItem(HttpServletRequest request) throws Exception {
        List<Item> allItem = (List<Item>) itemService.getAllItem();
        System.out.println("my item name" +allItem.get(0).getItemName());
        return new ResponseEntity<>(new ApiResponse(allItem, ApiStatus.success), HttpStatus.OK);
    }
    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity<ApiResponse> getItemByUser(@PathVariable long id, HttpServletRequest request) throws Exception {
        List<Item> items = itemService.getItemByUserId(id);
        return new ResponseEntity<>(new ApiResponse(items, ApiStatus.success), HttpStatus.OK);
    }


    @RequestMapping(method = DELETE, value = "/{itemName}/{id}")
    public ResponseEntity deleteUser(@PathVariable String itemName, @PathVariable long id,
                                                  HttpServletRequest request) throws Exception {
        itemService.deleteItem(itemName,id);
        return ResponseEntity.ok(itemName + " is deleted");
    }

  /*  @RequestMapping(method = GET, value = "/invoice/{id}")
    public ResponseEntity<ApiResponse> getInvoice(@PathVariable long id, HttpServletRequest request) throws Exception {
        List<Item> items = itemService.getItemByUserId(id);
        String totalAmount =itemService.getTotalAmount(id);
        Invoice invoice=new Invoice();
        invoice.setTotalAmount(totalAmount);
        invoice.setUserId(id);
        invoice.setItems(items);
        invoice.setInvoiceId(id);
        System.out.println("total invoice amount"+invoice.getTotalAmount());
        return new ResponseEntity<>(new ApiResponse(invoice, ApiStatus.success), HttpStatus.OK);
    }*/

}
