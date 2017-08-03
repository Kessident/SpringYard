package com.SpringYard.customer.service;

import com.SpringYard.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void testAddGet() {
        Customer customer1 = createTestCustomer();

        customerService.add(customer1);
        List<Customer> customers = customerService.getAll();

        Customer customer1Find = findInList(customers, customer1.getFirstName(), customer1.getLastName(), customer1.getEmail(), customer1.getPhone());

        Assert.assertNotNull("The first customer created should have created and found properly", customer1Find);
        Assert.assertEquals("Added and found customer should have same first name", customer1.getFirstName(), customer1Find.getFirstName());
        Assert.assertEquals("Added and found customer should have same last name", customer1.getLastName(), customer1Find.getLastName());
        Assert.assertEquals("Added and found customer should have same email", customer1.getEmail(), customer1Find.getEmail());
        Assert.assertEquals("Added and found customer should have same phone", customer1.getPhone(), customer1Find.getPhone());
    }

    @Test
    public void testUpdate() {
        Customer customer1 = createTestCustomer();
        customerService.add(customer1);

        List<Customer> customers = customerService.getAll();

        Customer customer1Update = findInList(customers, customer1.getFirstName(), customer1.getLastName(), customer1.getEmail(), customer1.getPhone());
        Assert.assertNotNull(customer1Update);

        customer1Update.setFirstName(Double.toString(Math.random()));
        customer1Update.setLastName(Double.toString(Math.random()));
        customerService.update(customer1Update);

        customers = customerService.getAll();

        Customer customer1AfterUpdate = findInList(customers, customer1Update.getFirstName(), customer1Update.getLastName(), customer1.getEmail(), customer1.getPhone());
        Assert.assertNotNull(customer1AfterUpdate);
        Assert.assertEquals(customer1Update.getId(), customer1AfterUpdate.getId());
    }

    @Test
    public void testDelete() {
        Customer customer1 = createTestCustomer();
        customerService.add(customer1);

        List<Customer> customers = customerService.getAll();

        Customer customer1Find = findInList(customers, customer1.getFirstName(), customer1.getLastName(), customer1.getEmail(), customer1.getPhone());
        Assert.assertNotNull(customer1Find);

        customerService.delete(customer1Find.getId());

        customers = customerService.getAll();
        Customer customer3 = findInList(customers, customer1.getFirstName(), customer1.getLastName(), customer1.getEmail(), customer1.getPhone());
        Assert.assertNull(customer3);
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer();
        customer.setEmail(Long.toString(System.currentTimeMillis()));
        customer.setFirstName(Double.toString(Math.random()));
        customer.setLastName(Double.toString(Math.random()));
        customer.setPhone(Long.toString(System.currentTimeMillis()));
        return customer;
    }
    private Customer findInList(List<Customer> customers, String firstName, String lastName, String email, String phone) {
        return customers.stream().filter(listItem ->
            listItem.getFirstName().equals(firstName) &&
            listItem.getLastName().equals(lastName) &&
            listItem.getEmail().equals(email) &&
            listItem.getPhone().equals(phone))
            .findFirst().orElse(null);
    }
}
