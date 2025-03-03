package com.br.minispc.debit.controllers;

import com.br.minispc.debit.dto.RequestDebitDto;
import com.br.minispc.debit.entities.DebitEntity;
import com.br.minispc.debit.services.DebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/debits")
public class DebitLayoutController {

    @Autowired
    private DebitService debitService;

    // Exibir formulário para criação da dívida
    @GetMapping("/create/{customerId}")
    public String showCreateForm(@PathVariable Long customerId, Model model) {
        model.addAttribute("customerId", customerId);
        return "debits/create";
    }

    // Processar o formulário de criação da dívida
    @PostMapping("/{customerId}/create")
    public String registerDebit(@PathVariable Long customerId,
                                @ModelAttribute RequestDebitDto requestDebitDto,
                                Model model) {

        DebitEntity debit = debitService.registerDivide(customerId, requestDebitDto);

        model.addAttribute("debit", debit);

        return "debits/confirmation";
    }

    // Listar todas as dívidas registradas
    @GetMapping("/list")
    public String listDebits(Model model) {
        model.addAttribute("debits", debitService.findAll());
        return "debits/list";
    }
}
