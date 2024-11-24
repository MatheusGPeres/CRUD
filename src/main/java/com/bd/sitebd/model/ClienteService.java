package com.bd.sitebd.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    // O serviço interage com o DAO, e a view não tem acesso direto ao DAO, só ao
    // Service

    @Autowired
    ClienteDAO clienteDAO;

    // Método para inserir um novo cliente no banco de dados
    public void inserir(Cliente cli) {
        clienteDAO.inserirCliente(cli); // OLHAR ESSE INSERIRCLIENTE, ANTES ERA SO INSERIR MAS TAVA ACUSANDO ERRO
    }

    // Método para obter todos os clientes do banco de dados
    public List<Map<String, Object>> obterTodosClientes() {
        return clienteDAO.obterTodosClientes();
    }

    // Método para atualizar as informações de um cliente no banco de dados
    public void atualizarCliente(int id, Cliente cli) {
        clienteDAO.atualizarCliente(id, cli);
    }

    // Método para obter um cliente específico do banco de dados com base no ID
    public Cliente obterCliente(int id) {
        return clienteDAO.obterCliente(id); // OLHAR E VER O QUE ESTÀ FAZENDO ESSE (CLIENTE) POIS FOI SUGESTÂO
                                            // DA
        // LAMPADA DO VS CODE PQ SEM ISSO TAVA ACUSANDO ERRO
    }

    // Método para deletar um cliente do banco de dados com base no ID
    public void deletarCliente(int id) {
        clienteDAO.deletarCliente(id);
    }
}
