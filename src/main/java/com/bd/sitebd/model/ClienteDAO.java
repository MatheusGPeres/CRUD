package com.bd.sitebd.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ClienteDAO {
    @Autowired
    DataSource dataSource;
    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, email, telefone, servico, horario, dia) VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql,
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getServico(),
                cliente.getHorario(),
                cliente.getDia());
    }

    public List<Map<String, Object>> obterTodosClientes() {
        String sql = "SELECT * FROM cliente";
        return jdbc.queryForList(sql);
    }

    public void atualizarCliente(int id, Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, email = ?, telefone = ?, servico = ?, horario = ?, dia = ? WHERE id = ?";
        jdbc.update(sql,
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getServico(),
                cliente.getHorario(),
                cliente.getDia(),
                id);
    }

    public Cliente obterCliente(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        return Tool.converterCliente(jdbc.queryForMap(sql, id));
    }

    public void deletarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        jdbc.update(sql, id);
    }
}
