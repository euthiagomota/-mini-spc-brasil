package com.br.minispc.debt.controllers;

import com.br.minispc.debt.dto.RequestDebtDto;
import com.br.minispc.debt.entities.DebtEntity;
import com.br.minispc.debt.services.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/debts")
public class DebtLayoutController {

    @Autowired
    private DebtService debtService;

    // Exibir formulário para criação da dívida
    @GetMapping("/create/{customerId}")
    public String showCreateForm(@PathVariable Long customerId, Model model) {
        model.addAttribute("customerId", customerId);
        return "debts/create";
    }

    // Processar o formulário de criação da dívida
    @PostMapping("/{customerId}/create")
    public String registerdebt(@PathVariable Long customerId,
                                @ModelAttribute RequestDebtDto requestdebtDto,
                                Model model) {

        DebtEntity debt = debtService.registerDivide(customerId, requestdebtDto);

        model.addAttribute("debt", debt);

        return "debts/confirmation";
    }

    // Listar todas as dívidas registradas
    @GetMapping("/list")
    public String listdebts(Model model) {
        model.addAttribute("debts", debtService.findAll());
        return "debts/list";
    }
}
