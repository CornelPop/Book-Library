package org.example.model.builder;

import org.example.model.Bill;

import java.time.LocalDate;

public class BillBuilder {

    private Bill bill;

    public BillBuilder(){
        bill = new Bill();
    }

    public BillBuilder setId(Long id){
        bill.setId(id);
        return this;
    }

    public BillBuilder setQuantity(int quantity){
        bill.setQuantity(quantity);
        return this;
    }

    public BillBuilder setAmountPaid(int amountPaid) {
        bill.setAmountPaid(amountPaid);
        return this;
    }

    public BillBuilder setBookId (Long bookId)
    {
        bill.setBook_id(bookId);
        return this;
    }

    public BillBuilder setCustomerId (int customerId)
    {
        bill.setCustomer_id(customerId);
        return this;
    }
    public Bill build(){
        return bill;
    }

}
