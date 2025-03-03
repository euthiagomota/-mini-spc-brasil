package com.br.minispc.customer.controllers;

import com.br.minispc.customer.dto.RequestCustomerDto;
import com.br.minispc.customer.entities.CustomerEntity;
import com.br.minispc.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

//    @GetMapping("/view")
//    public String showCustomerPage(Model model) {
//        List<CustomerEntity> customers = customerService.listAll();
//        model.addAttribute("customers", customers);
//        return "customers";
//    }

    @GetMapping("/view")
    public String listCustomers(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerEntity> customerPage = customerService.findAll(pageable);

        model.addAttribute("customers", customerPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", customerPage.getTotalPages());

        return "customers";
    }

    @GetMapping("/details")
    public String findCustomerByCpf(@RequestParam String cpf, Model model) {
        CustomerEntity customer = this.customerService.findCustomerAllData(cpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

            model.addAttribute("customer", customer);

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
