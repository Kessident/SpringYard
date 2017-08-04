package com.SpringYard.customer;

    import com.SpringYard.customer.model.Customer;
    import com.SpringYard.customer.service.CustomerService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;

    import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/customer/view/all")
    public String viewAllCustomers(Model model){
        List<Customer> customerList = customerService.getAll();
        model.addAttribute("customerList", customerList);
        return "customers";
    }

    @RequestMapping("/customer/add")
    public String addCustomerGet(){
        return "add_customer";
    }

    @RequestMapping(path="/customer/add", method = RequestMethod.POST)
    public String addCustomerPost(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phone, @RequestParam String email){
        Customer newCustomer = new Customer(firstName, lastName, phone, email);
        customerService.add(newCustomer);
        return "redirect:/customer/view/all";
    }

    @RequestMapping("/customer/view")
    public String viewCustomer(@RequestParam int id, Model model){
        Customer customer = customerService.getByID(id);
        if (customer != null){
            model.addAttribute("customer", customer);
            return "view_customer";
        } else {
            return "customer_not_found";
        }
    }

}