package com.SpringYard.customer.service;

import com.SpringYard.customer.model.Customer;
import com.SpringYard.customer.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @Override
    public void add(Customer customer) {
        customerRepository.add(customer);
    }

    @Transactional
    @Override
    public void add(List<Customer> customers){

    }

    @Transactional
    @Override
    public void update(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public Customer getByID(int id) {
        return customerRepository.getByID(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Transactional
    @Override
    public void delete(int id) {
        customerRepository.delete(id);
    }
}
