package br.clinica.dao;

import br.clinica.db.DBConnection;
import br.clinica.model.Consulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void insert(Consulta cObj) throws SQLException {
        String sql = "INSERT INTO consulta (data, hora, observacoes, id_paciente, id_medico) VALUES (?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, cObj.getData());
            ps.setTime(2, cObj.getHora());
            ps.setString(3, cObj.getObservacoes());
            ps.setInt(4, cObj.getIdPaciente());
            ps.setInt(5, cObj.getIdMedico());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) cObj.setId(rs.getInt(1));
            }
        }
    }

    public void update(Consulta cObj) throws SQLException {
        String sql = "UPDATE consulta SET data=?, hora=?, observacoes=?, id_paciente=?, id_medico=? WHERE id_consulta=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate(1, cObj.getData());
            ps.setTime(2, cObj.getHora());
            ps.setString(3, cObj.getObservacoes());
            ps.setInt(4, cObj.getIdPaciente());
            ps.setInt(5, cObj.getIdMedico());
            ps.setInt(6, cObj.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM consulta WHERE id_consulta=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Consulta findById(int id) throws SQLException {
        String sql = "SELECT * FROM consulta WHERE id_consulta=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Consulta co = new Consulta();
                    co.setId(rs.getInt("id_consulta"));
                    co.setData(rs.getDate("data"));
                    co.setHora(rs.getTime("hora"));
                    co.setObservacoes(rs.getString("observacoes"));
                    co.setIdPaciente(rs.getInt("id_paciente"));
                    co.setIdMedico(rs.getInt("id_medico"));
                    return co;
                }
            }
        }
        return null;
    }

    public List<Consulta> findAll() throws SQLException {
        String sql = "SELECT * FROM consulta";
        List<Consulta> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Consulta co = new Consulta();
                co.setId(rs.getInt("id_consulta"));
                co.setData(rs.getDate("data"));
                co.setHora(rs.getTime("hora"));
                co.setObservacoes(rs.getString("observacoes"));
                co.setIdPaciente(rs.getInt("id_paciente"));
                co.setIdMedico(rs.getInt("id_medico"));
                list.add(co);
            }
        }
        return list;
    }

    // JOIN example: list consultas with paciente and medico names
    public List<String> listConsultasComPacienteMedico() throws SQLException {
        String sql = "SELECT c.id_consulta, c.data, c.hora, p.nome AS paciente, m.nome AS medico " +
                     "FROM consulta c " +
                     "JOIN paciente p ON c.id_paciente = p.id_paciente " +
                     "JOIN medico m ON c.id_medico = m.id_medico";
        List<String> out = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(rs.getInt("id_consulta") + " | " + rs.getDate("data") + " " + rs.getTime("hora") + " | " +
                        rs.getString("paciente") + " | " + rs.getString("medico"));
            }
        }
        return out;
    }
}
