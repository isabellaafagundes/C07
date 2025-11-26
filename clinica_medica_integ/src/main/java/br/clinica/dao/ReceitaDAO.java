package br.clinica.dao;

import br.clinica.db.DBConnection;
import br.clinica.model.Receita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {

    public void insert(Receita r) throws SQLException {
        String sql = "INSERT INTO receita (descricao, data_emissao, validade, id_consulta) VALUES (?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getDescricao());
            ps.setDate(2, r.getDataEmissao());
            ps.setDate(3, r.getValidade());
            ps.setInt(4, r.getIdConsulta());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setId(rs.getInt(1));
            }
        }
    }

    public void update(Receita r) throws SQLException {
        String sql = "UPDATE receita SET descricao=?, data_emissao=?, validade=?, id_consulta=? WHERE id_receita=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, r.getDescricao());
            ps.setDate(2, r.getDataEmissao());
            ps.setDate(3, r.getValidade());
            ps.setInt(4, r.getIdConsulta());
            ps.setInt(5, r.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM receita WHERE id_receita=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Receita findById(int id) throws SQLException {
        String sql = "SELECT * FROM receita WHERE id_receita=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Receita r = new Receita();
                    r.setId(rs.getInt("id_receita"));
                    r.setDescricao(rs.getString("descricao"));
                    r.setDataEmissao(rs.getDate("data_emissao"));
                    r.setValidade(rs.getDate("validade"));
                    r.setIdConsulta(rs.getInt("id_consulta"));
                    return r;
                }
            }
        }
        return null;
    }

    public List<Receita> findAll() throws SQLException {
        String sql = "SELECT * FROM receita";
        List<Receita> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Receita r = new Receita();
                r.setId(rs.getInt("id_receita"));
                r.setDescricao(rs.getString("descricao"));
                r.setDataEmissao(rs.getDate("data_emissao"));
                r.setValidade(rs.getDate("validade"));
                r.setIdConsulta(rs.getInt("id_consulta"));
                list.add(r);
            }
        }
        return list;
    }

    // JOIN example: receita + paciente via consulta
    public List<String> listReceitasComPaciente() throws SQLException {
        String sql = "SELECT r.id_receita, r.descricao, r.data_emissao, p.nome AS paciente, c.data AS data_consulta " +
                     "FROM receita r " +
                     "JOIN consulta c ON r.id_consulta = c.id_consulta " +
                     "JOIN paciente p ON c.id_paciente = p.id_paciente";
        List<String> out = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(rs.getInt("id_receita") + " | " + rs.getString("descricao") + " | " + rs.getString("paciente") + " | " + rs.getDate("data_consulta"));
            }
        }
        return out;
    }
}
