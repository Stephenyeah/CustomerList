package fi.haagahelia.customer.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class CustomerDAOImpl implements CustomerDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Customer customer) {
        if (existsByFullName(customer.getFirstName(), customer.getLastName())) {
            // Check if a customer with the same first name and last name already exists in the database
            System.out.println("The name has existed");
            // If a customer with the same first name and last name already exists, print a warning and return
            return;
        }

        String sql = "insert into customer(first_name, last_name) values(?,?)";
        String[] parameters = new String[]{ customer.getFirstName(), customer.getLastName()};

        jdbcTemplate.update(sql, parameters);
    }


    public Customer findOne(int id) {
        String sql = "select customer_id, first_name, last_name from customer where customer_id = ?";
        Object[] parameters = new Object[]{ id };
        RowMapper<Customer> mapper = new CustomerRowMapper();

        Customer customer = jdbcTemplate.queryForObject(sql, parameters,mapper);
        return customer;
    }


    public List<Customer> findAll() {

        String sql = "select customer_id, first_name, last_name from customer";
        RowMapper<Customer> mapper = new CustomerRowMapper();
        List<Customer> customers = jdbcTemplate.query(sql, mapper);

        return customers;
    }

    public boolean existsByFullName(String firstName, String lastName) {
        // Write an SQL query to check if a customer with the specified first name and last name already exists in the database
        String sql = "SELECT COUNT(*) FROM customer WHERE first_name = ? AND last_name = ?";
        Object[] parameters = new Object[]{firstName, lastName};

        // Execute the SQL query and return the result
        int count = jdbcTemplate.queryForObject(sql, parameters, Integer.class);
        return count > 0;
    }
}
