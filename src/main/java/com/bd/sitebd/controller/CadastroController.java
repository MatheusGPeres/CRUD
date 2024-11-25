package com.bd.sitebd.controller;

//Acessar dados que vão ser usados para trabalhar com listas e mapas.
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

//Importando as classes Cliente e ClienteService, que representam o modelo de dados (cliente) e a lógica de negócio (serviço para manipular os clientes) da aplicação.
import com.bd.sitebd.model.Cliente;
import com.bd.sitebd.model.ClienteService;

//Indica que é um Controller 
@Controller
public class CadastroController {

    @Autowired
    ApplicationContext context;

    //Mapeia uma URL (no caso, a raiz)
    @GetMapping("/")
    public String home() { //Ao acessar a página inicial do site, esse método é chamado.
        return "principal";
    }

    @GetMapping("/atualizar/{id}") //O {id} é uma variável que será substituída pelo valor real na URL (como /atualizar/1).
    public String atualizar(Model model, @PathVariable int id) {
        ClienteService cs = context.getBean(ClienteService.class);
        Cliente cli = cs.obterCliente(id);
        model.addAttribute("id", id);
        model.addAttribute("cliente", cli);
        return "atualizar";
    }

    // Processamento da atualização de cliente
    @PostMapping("/atualizar/{id}") //Esse método será chamado quando um formulário de atualização de cliente for enviado.
    public String atualizarCliente(@PathVariable int id, @ModelAttribute Cliente cliente) { //@PathVariable captura o valor da URL ({id}) e os passa como parâmetro
        ClienteService cs = context.getBean(ClienteService.class);
        cs.atualizarCliente(id, cliente);
        return "atualizou";
    }

    // Página de agendamento
    @GetMapping("/agendamento")
    public String agendamento(Model model) {
        model.addAttribute("cliente", new Cliente()); //Cria um novo objeto cliente em branco para ser usado na view.
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
        return "redirect:/tabela"; //Redirect garante recarregar a página
    }
}
