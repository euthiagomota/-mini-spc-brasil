package com.br.minispc.customer.controllers;

import com.br.minispc.customer.dto.RequestCustomerDto;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/view")
    public String showCustomerPage(Model model) {
        List<CustomerEntity> customers = customerService.listAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/details")
    public String findCustomerByCpf(@RequestParam String cpf, Model model) {
        Optional<CustomerEntity> customer = this.customerService.findCustomer(cpf);
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
        } else {
            model.addAttribute("error", "Cliente não encontrado.");
        }
        return "customerDetails";
    }

    @GetMapping("/register")
    public String showCreateCustomerPage() {
        return "createCustomer";
    }

    @PostMapping("/created")
    public String createCustomer(@ModelAttribute RequestCustomerDto requestCustomerDto) {
        customerService.registerCustomer(requestCustomerDto);
        return "redirect:/customers/view";
    }

}
