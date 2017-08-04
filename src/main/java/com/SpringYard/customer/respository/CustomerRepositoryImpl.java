package com.SpringYard.customer.respository;

import com.SpringYard.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private JdbcTemplate database;

    @Override
    public void add(Customer customer) {
        final String ADD_SQL = "INSERT INTO customer (firstName, lastName, phone, email) VALUES (?,?,?,?)";
        database.update(ADD_SQL, customer.getFirstName(), customer.getLastName(), customer.getPhone(), customer.getEmail());
    }

    @Override
    public void add(List<Customer> customers) {
        customers.forEach(this::add);
    }

    @Override
    public void update(Customer customer) {
        final String UPDATE_SQL = "UPDATE customer SET firstName=?, lastName=?, phone=?, email=? WHERE id=?";
        database.update(UPDATE_SQL, customer.getFirstName(), customer.getLastName(), customer.getPhone(), customer.getEmail(), customer.getId());
    }

    @Override
    public Customer getByID(int id) {
        final String GET_BY_ID_SQL = "SELECT * FROM customer WHERE id=?";
        return database.queryForObject(GET_BY_ID_SQL, new CustomerMapper(), id);
    }

    @Override
    public List<Customer> getAll() {
        final String GET_ALL_SQL = "SELECT * FROM customer";
        return database.query(GET_ALL_SQL, new CustomerMapper());
    }

    @Override
    public void delete(int id) {
        final String DELETE_ID_SQL = "DELETE FROM customer WHERE id=?";
        database.update(DELETE_ID_SQL, id);
    }

    private static class CustomerMapper implements RowMapper<Customer>{
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("lastName"));
            customer.setPhone(rs.getString("phone"));
            customer.setEmail(rs.getString("email"));
            return customer;
        }
    }
}
