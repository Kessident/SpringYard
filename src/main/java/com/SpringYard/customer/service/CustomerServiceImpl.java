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
        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void add(List<Customer> customers){
        customerRepository.save(customers);
    }

    @Transactional
    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getByID(int id) {
        return customerRepository.getOne(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(int id) {
        customerRepository.delete(id);
    }
}
