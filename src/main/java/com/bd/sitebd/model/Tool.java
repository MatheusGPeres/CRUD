package com.bd.sitebd.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Tool {

    // Método para converter um registro do banco de dados (Map) para um objeto
    // Cliente
    public static Cliente converterCliente(Map<String, Object> registro) {
        return new Cliente(
                (Integer) registro.get("id"), //Chave (id, do tipo string) - Valor(conteúdo do id) - isso é o Map<String, Object>, uma estrutura de dados que guarda dados do tipo chave-valor
                (String) registro.get("nome"), //Isso aqui que vai pegar o valor da chave nome e converter para string
                (String) registro.get("email"),
                (String) registro.get("telefone"),
                (String) registro.get("servico"),
                registro.get("horario") instanceof Time
                        ? ((Time) registro.get("horario")).toLocalTime()
                        : (LocalTime) registro.get("horario"),
                registro.get("dia") instanceof Date
                        ? ((Date) registro.get("dia")).toLocalDate()
                        : (LocalDate) registro.get("dia")); //Verifica se o valor da chave "horario" e "dia são do tipo Time/Date (que representa horários e datas no banco de dados).
    }
}
