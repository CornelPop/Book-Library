package org.example.model;

public class Bill {

    private Long id;
    private Long book_id;
    private int customer_id;
    private int quantity;
    private int amountPaid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", bookId=" + book_id +
                ", customerId=" + customer_id +
                ", quantity=" + quantity +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
