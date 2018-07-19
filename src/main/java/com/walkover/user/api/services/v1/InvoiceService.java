package com.walkover.user.api.services.v1;

import com.walkover.user.api.dao.model.Invoice;
import com.walkover.user.api.dao.model.Item;
import com.walkover.user.api.dao.repository.InvoiceRepository;
import com.walkover.user.api.dao.repository.ItemRepository;
import com.walkover.user.api.dao.repository.UserRepository;
import com.walkover.user.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceService {

   private final InvoiceRepository invoiceRepository;
    private ItemRepository itemRepository;
  //  private UserRepository userRepository;

    @Autowired
   public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    @Autowired
    ItemService itemService =new ItemService(itemRepository);

    @Transactional
    public Invoice createInvoice(long userId) throws NotFoundException
    {
       List<Item> items = itemService.getItemByUserId(userId);
        String totalAmount =itemService.getTotalAmount(userId);
        Invoice invoice=new Invoice();
        invoice.setTotalAmount(totalAmount);
        invoice.setUserId(userId);
        invoice.setItems(items);
       // invoice.setInvoiceId(userId);
        invoiceRepository.save(invoice);
        return invoice;
    }
}
