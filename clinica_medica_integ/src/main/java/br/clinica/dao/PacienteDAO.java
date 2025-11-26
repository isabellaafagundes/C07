package br.clinica.dao;

import br.clinica.db.DBConnection;
import br.clinica.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void insert(Paciente p) throws SQLException {
        String sql = "INSERT INTO paciente (nome, data_nascimento, telefone, endereco) VALUES (?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNome());
            ps.setDate(2, p.getDataNascimento());
            ps.setString(3, p.getTelefone());
            ps.setString(4, p.getEndereco());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(rs.getInt(1));
            }
        }
    }

    public void update(Paciente p) throws SQLException {
        String sql = "UPDATE paciente SET nome=?, data_nascimento=?, telefone=?, endereco=? WHERE id_paciente=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setDate(2, p.getDataNascimento());
            ps.setString(3, p.getTelefone());
            ps.setString(4, p.getEndereco());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM paciente WHERE id_paciente=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Paciente findById(int id) throws SQLException {
        String sql = "SELECT * FROM paciente WHERE id_paciente=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Paciente p = new Paciente();
                    p.setId(rs.getInt("id_paciente"));
                    p.setNome(rs.getString("nome"));
                    p.setDataNascimento(rs.getDate("data_nascimento"));
                    p.setTelefone(rs.getString("telefone"));
                    p.setEndereco(rs.getString("endereco"));
                    return p;
                }
            }
        }
        return null;
    }

    public List<Paciente> findAll() throws SQLException {
        String sql = "SELECT * FROM paciente";
        List<Paciente> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id_paciente"));
                p.setNome(rs.getString("nome"));
                p.setDataNascimento(rs.getDate("data_nascimento"));
                p.setTelefone(rs.getString("telefone"));
                p.setEndereco(rs.getString("endereco"));
                list.add(p);
            }
        }
        return list;
    }
}
