package com.bd.sitebd.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bd.sitebd.model.Cliente;
import com.bd.sitebd.model.ClienteService;

@Controller
public class CadastroController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String home() {
        return "principal";
    }

    @GetMapping("/atualizar/{id}")
    public String atualizar(Model model, @PathVariable int id) {
        ClienteService cs = context.getBean(ClienteService.class);
        Cliente cli = cs.obterCliente(id);
        model.addAttribute("id", id);
        model.addAttribute("cliente", cli);
        return "atualizar";
    }

    // Processamento da atualização de cliente
    @PostMapping("/atualizar/{id}")
    public String atualizarCliente(@PathVariable int id, @ModelAttribute Cliente cliente) {
        ClienteService cs = context.getBean(ClienteService.class);
        cs.atualizarCliente(id, cliente);
        return "atualizou";
    }

    // Página de agendamento
    @GetMapping("/agendamento")
    public String agendamento(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "agendamento";
    }

    // Processamento do formulário de agendamento
    @PostMapping("/agendamento")
    public String agendar(Model model, @ModelAttribute Cliente cliente) {
        ClienteService cs = context.getBean(ClienteService.class);
        try {
            cs.inserir(cliente);
            return "sucesso"; // Redireciona para a página de sucesso
        } catch (Exception e) {
            model.addAttribute("erro");
            return "erro"; // Redireciona para a página de erro
        }
    }

    // Página de tabela de registros
    @GetMapping("/tabela")
    public String tabela(Model model) {
        ClienteService cs = context.getBean(ClienteService.class);
        List<Map<String, Object>> lista = cs.obterTodosClientes();
        model.addAttribute("clientes", lista);
        return "tabela";
    }

    // Processamento da exclusão de cliente
    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable int id) {
        ClienteService cs = context.getBean(ClienteService.class);
        cs.deletarCliente(id);
        return "redirect:/tabela";
    }
}

// // Página de sucesso após agendamento
// @GetMapping("/sucesso")
// public String sucesso() {
// return "tabela";
// }
