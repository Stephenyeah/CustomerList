package fi.haagahelia.customer.web;

import fi.haagahelia.customer.domain.Customer;
import fi.haagahelia.customer.domain.CustomerDAO;
import fi.haagahelia.customer.domain.CustomerDAOImpl;
import fi.haagahelia.customer.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private CustomerDAOImpl customerDAOImpl;

    @Autowired
    private CustomerRepository repository;

    @RequestMapping(value = "/customerlist")
    public String customerList(Model model){
        // Fatch all customer
        List<Customer> customers = repository.findAll();

        //add customerlist
        model.addAttribute("customers",customers);
        return "customerlist";
    }

    // add new customer
    @RequestMapping(value = "/add")
    public  String addCustomer(Model model){

        model.addAttribute("customer",new Customer());
        return  "addcustomer";
    }

    //save new customer
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public  String save(Customer customer, Model model){

        if (customerDAOImpl.existsByFullName(customer.getFirstName(), customer.getLastName())) {
            // If the customer already exists, add an alert message to the model
            model.addAttribute("alertMessage", "Customer with the same name already exists");
            // Return the addcustomer.html template with the alert message
            System.out.println("The name has existed");
            return "addcustomer";
        }
        customerDAO.save(customer);
        return "redirect:customerlist";
    }


}
