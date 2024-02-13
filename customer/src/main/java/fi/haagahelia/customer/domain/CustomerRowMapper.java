package fi.haagahelia.customer.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException{
        Customer customer =new Customer();
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setId(rs.getLong("customer_id"));

        return customer;
    }
}
