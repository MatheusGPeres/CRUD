package com.bd.sitebd.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String servico;
    private LocalTime horario;
    private LocalDate dia;

    // Construtor vazio
    public Cliente() {
    }

    // Construtor completo
    public Cliente(int id, String nome, String email, String telefone, String servico, LocalTime horario,
            LocalDate dia) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.servico = servico;
        this.horario = horario;
        this.dia = dia;
    }

    // Construtor para criar sem ID (quando o ID é gerado automaticamente pelo
    // banco)
    public Cliente(String nome, String email, String telefone, String servico, LocalTime horario, LocalDate dia) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.servico = servico;
        this.horario = horario;
        this.dia = dia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    // Método toString (opcional, pode ser descomentado se necessário)
    // @Override
    // public String toString() {
    // return "Cliente{" +
    // "id=" + id +
    // ", nome='" + nome + '\'' +
    // ", email='" + email + '\'' +
    // ", telefone='" + telefone + '\'' +
    // ", servico='" + servico + '\'' +
    // ", horario='" + horario + '\'' +
    // ", data='" + data + '\'' +
    // '}';
    // }
}
