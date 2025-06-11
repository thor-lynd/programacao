package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/seubanco"; // ajuste seu banco
        String user = "root";
        String password = "sua_senha";
        return DriverManager.getConnection(url, user, password);
    }

    public void inserir(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, idade, ocupacao) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pessoa.getNomePessoa());
            ps.setInt(2, pessoa.getIdadePessoa());
            ps.setString(3, pessoa.getOcupacaoPessoa());
            ps.executeUpdate();
        }
    }

    public List<Pessoa> listarTodos() throws SQLException {
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT nome, idade, ocupacao FROM pessoa";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String ocupacao = rs.getString("ocupacao");
                lista.add(new Pessoa(nome, idade, ocupacao));
            }
        }
        return lista;
    }

    public void atualizar(Pessoa pessoa, String nomeAntigo) throws SQLException {
        String sql = "UPDATE pessoa SET nome = ?, idade = ?, ocupacao = ? WHERE nome = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pessoa.getNomePessoa());
            ps.setInt(2, pessoa.getIdadePessoa());
            ps.setString(3, pessoa.getOcupacaoPessoa());
            ps.setString(4, nomeAntigo);
            ps.executeUpdate();
        }
    }
}
