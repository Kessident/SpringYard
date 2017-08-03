package com.SpringYard.customer.respository;

import com.SpringYard.customer.model.Customer;

import java.util.List;

public interface CustomerRepository {
    void add (Customer customer);
    void add(List<Customer> customers);
    void update(Customer customer);
    Customer getByID(int id);
    List<Customer> getAll();
    void delete(int id);
}
