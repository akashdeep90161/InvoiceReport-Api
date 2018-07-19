package com.walkover.user.api.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.walkover.user.api.dao.model.Invoice;
import com.walkover.user.api.dao.model.Item;
import com.walkover.user.api.dao.model.User;
import com.walkover.user.api.dao.repository.ItemRepository;
import com.walkover.user.api.dao.repository.UserRepository;
import com.walkover.user.api.models.commens.ApiResponse;
import com.walkover.user.api.models.commens.ApiStatus;
import com.walkover.user.api.resources.v1.ItemResource;
import com.walkover.user.api.services.v1.InvoiceService;
import com.walkover.user.api.services.v1.ItemService;
import com.walkover.user.api.services.v1.UserService;
import com.walkover.user.api.utils.commons.JsonViews;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

   private final InvoiceService invoiceService;

  public InvoiceController(InvoiceService invoiceService) {
      this.invoiceService = invoiceService;
   }
   @RequestMapping(method = GET, value = "/{id}")
   public ResponseEntity<ApiResponse> getInvoice(@PathVariable long id, HttpServletRequest request) throws Exception {
      Invoice invoice = invoiceService.createInvoice(id);
      return new ResponseEntity<>(new ApiResponse(invoice, ApiStatus.success), HttpStatus.OK);
   }

}
