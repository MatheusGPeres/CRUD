package com.bd.sitebd.model;

import java.util.List;
import java.util.Map;

// Importação do DataSource (conectar com o banco de dados)
import javax.sql.DataSource;

// Importação do JdbcTemplate (ferramenta do Spring que facilitaa a interação com o banco de dados)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;


@Repository // Diz ao Spring que esta classe é um componente de acesso a dados.
public class ClienteDAO {
    @Autowired //Sem o @Autowired, teria que instanciar manualmente.
    DataSource dataSource; //Configura conexão com o banco de dados automaticamente baseado no application.properties ou no application.yml.
    JdbcTemplate jdbc;

    @PostConstruct //O método é executado automaticamente pelo Spring após o bean ser instanciado e as dependências injetadas.
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource); // Inicializa o JdbcTemplate e conecta ao banco de dados usando o DataSource.
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
