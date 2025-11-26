package br.clinica.app;

import br.clinica.dao.*;
import br.clinica.model.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final PacienteDAO pacienteDAO = new PacienteDAO();
    private static final MedicoDAO medicoDAO = new MedicoDAO();
    private static final EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
    private static final ConsultaDAO consultaDAO = new ConsultaDAO();
    private static final ReceitaDAO receitaDAO = new ReceitaDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Clinica Menu ---");
            System.out.println("1. Paciente - Listar");
            System.out.println("2. Paciente - Inserir");
            System.out.println("3. Paciente - Atualizar");
            System.out.println("4. Paciente - Deletar");
            System.out.println("5. Medico - Listar");
            System.out.println("6. Medico - Inserir");
            System.out.println("7. Medico - Atualizar");
            System.out.println("8. Medico - Deletar");
            System.out.println("9. Especialidade - Listar");
            System.out.println("10. Especialidade - Inserir");
            System.out.println("11. Especialidade - Atualizar");
            System.out.println("12. Especialidade - Deletar");
            System.out.println("13. Consulta - Listar");
            System.out.println("14. Consulta - Inserir");
            System.out.println("15. Consulta - Atualizar");
            System.out.println("16. Consulta - Deletar");
            System.out.println("17. Receita - Listar");
            System.out.println("18. Receita - Inserir");
            System.out.println("19. Receita - Atualizar");
            System.out.println("20. Receita - Deletar");
            System.out.println("21. JOINs: Consultas com paciente/medico");
            System.out.println("22. JOINs: Medicos com especialidades");
            System.out.println("23. JOINs: Receitas com paciente via consulta");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int op = Integer.parseInt(sc.nextLine());
            try {
                switch (op) {
                    case 1 -> listarPacientes();
                    case 2 -> inserirPaciente();
                    case 3 -> atualizarPaciente();
                    case 4 -> deletarPaciente();
                    case 5 -> listarMedicos();
                    case 6 -> inserirMedico();
                    case 7 -> atualizarMedico();
                    case 8 -> deletarMedico();
                    case 9 -> listarEspecialidades();
                    case 10 -> inserirEspecialidade();
                    case 11 -> atualizarEspecialidade();
                    case 12 -> deletarEspecialidade();
                    case 13 -> listarConsultas();
                    case 14 -> inserirConsulta();
                    case 15 -> atualizarConsulta();
                    case 16 -> deletarConsulta();
                    case 17 -> listarReceitas();
                    case 18 -> inserirReceita();
                    case 19 -> atualizarReceita();
                    case 20 -> deletarReceita();
                    case 21 -> listarConsultasComJoin();
                    case 22 -> listarMedicosComEspecialidades();
                    case 23 -> listarReceitasComPaciente();
                    case 0 -> { System.out.println("Saindo..."); return; }
                    default -> System.out.println("Opcao invalida");
                }
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex.getMessage());
            }
        }
    }

    private static void listarPacientes() throws SQLException {
        List<Paciente> list = pacienteDAO.findAll();
        for (Paciente p : list) {
            System.out.println(p.getId() + " | " + p.getNome() + " | " + p.getDataNascimento());
        }
    }

    private static void inserirPaciente() throws SQLException {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Data nascimento (YYYY-MM-DD): "); Date data = Date.valueOf(sc.nextLine());
        System.out.print("Telefone: "); String tel = sc.nextLine();
        System.out.print("Endereco: "); String end = sc.nextLine();
        Paciente p = new Paciente(null, nome, data, tel, end);
        pacienteDAO.insert(p);
        System.out.println("Inserido com id: " + p.getId());
    }

    private static void atualizarPaciente() throws SQLException {
        System.out.print("Id paciente: "); int id = Integer.parseInt(sc.nextLine());
        Paciente p = pacienteDAO.findById(id);
        if (p == null) { System.out.println("Paciente nao encontrado."); return; }
        System.out.print("Nome (" + p.getNome() + "): "); String nome = sc.nextLine(); if (!nome.isEmpty()) p.setNome(nome);
        System.out.print("Data nascimento (" + p.getDataNascimento() + "): "); String datas = sc.nextLine(); if (!datas.isEmpty()) p.setDataNascimento(Date.valueOf(datas));
        System.out.print("Telefone (" + p.getTelefone() + "): "); String tel = sc.nextLine(); if (!tel.isEmpty()) p.setTelefone(tel);
        System.out.print("Endereco (" + p.getEndereco() + "): "); String end = sc.nextLine(); if (!end.isEmpty()) p.setEndereco(end);
        pacienteDAO.update(p);
        System.out.println("Atualizado.");
    }

    private static void deletarPaciente() throws SQLException {
        System.out.print("Id paciente: "); int id = Integer.parseInt(sc.nextLine());
        pacienteDAO.delete(id);
        System.out.println("Deletado.");
    }

    private static void listarMedicos() throws SQLException {
        List<Medico> list = medicoDAO.findAll();
        for (Medico m : list) {
            System.out.println(m.getId() + " | " + m.getNome() + " | " + m.getCrm());
        }
    }

    private static void inserirMedico() throws SQLException {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("CRM: "); String crm = sc.nextLine();
        System.out.print("Telefone: "); String tel = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        Medico m = new Medico(null, nome, crm, tel, email);
        medicoDAO.insert(m);
        System.out.println("Inserido com id: " + m.getId());
    }

    private static void atualizarMedico() throws SQLException {
        System.out.print("Id medico: "); int id = Integer.parseInt(sc.nextLine());
        Medico m = medicoDAO.findById(id);
        if (m == null) { System.out.println("Medico nao encontrado."); return; }
        System.out.print("Nome (" + m.getNome() + "): "); String nome = sc.nextLine(); if (!nome.isEmpty()) m.setNome(nome);
        System.out.print("CRM (" + m.getCrm() + "): "); String crm = sc.nextLine(); if (!crm.isEmpty()) m.setCrm(crm);
        System.out.print("Telefone (" + m.getTelefone() + "): "); String tel = sc.nextLine(); if (!tel.isEmpty()) m.setTelefone(tel);
        System.out.print("Email (" + m.getEmail() + "): "); String email = sc.nextLine(); if (!email.isEmpty()) m.setEmail(email);
        medicoDAO.update(m);
        System.out.println("Atualizado.");
    }

    private static void deletarMedico() throws SQLException {
        System.out.print("Id medico: "); int id = Integer.parseInt(sc.nextLine());
        medicoDAO.delete(id);
        System.out.println("Deletado.");
    }

    private static void listarEspecialidades() throws SQLException {
        List<Especialidade> list = especialidadeDAO.findAll();
        for (Especialidade e : list) {
            System.out.println(e.getId() + " | " + e.getNome() + " | " + e.getDescricao());
        }
    }

    private static void inserirEspecialidade() throws SQLException {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Descricao: "); String desc = sc.nextLine();
        Especialidade e = new Especialidade(null, nome, desc);
        especialidadeDAO.insert(e);
        System.out.println("Inserido com id: " + e.getId());
    }

    private static void atualizarEspecialidade() throws SQLException {
        System.out.print("Id especialidade: "); int id = Integer.parseInt(sc.nextLine());
        Especialidade e = especialidadeDAO.findById(id);
        if (e == null) { System.out.println("Especialidade nao encontrada."); return; }
        System.out.print("Nome (" + e.getNome() + "): "); String nome = sc.nextLine(); if (!nome.isEmpty()) e.setNome(nome);
        System.out.print("Descricao (" + e.getDescricao() + "): "); String desc = sc.nextLine(); if (!desc.isEmpty()) e.setDescricao(desc);
        especialidadeDAO.update(e);
        System.out.println("Atualizado.");
    }

    private static void deletarEspecialidade() throws SQLException {
        System.out.print("Id especialidade: "); int id = Integer.parseInt(sc.nextLine());
        especialidadeDAO.delete(id);
        System.out.println("Deletado.");
    }

    private static void listarConsultas() throws SQLException {
        List<Consulta> list = consultaDAO.findAll();
        for (Consulta c : list) {
            System.out.println(c.getId() + " | " + c.getData() + " " + c.getHora() + " | pacienteId=" + c.getIdPaciente() + " | medicoId=" + c.getIdMedico());
        }
    }

    private static void inserirConsulta() throws SQLException {
        System.out.print("Data (YYYY-MM-DD): "); Date data = Date.valueOf(sc.nextLine());
        System.out.print("Hora (HH:MM:SS): "); Time hora = Time.valueOf(sc.nextLine());
        System.out.print("Observacoes: "); String obs = sc.nextLine();
        System.out.print("Id paciente: "); int idp = Integer.parseInt(sc.nextLine());
        System.out.print("Id medico: "); int idm = Integer.parseInt(sc.nextLine());
        Consulta c = new Consulta(null, data, hora, obs, idp, idm);
        consultaDAO.insert(c);
        System.out.println("Inserido com id: " + c.getId());
    }

    private static void atualizarConsulta() throws SQLException {
        System.out.print("Id consulta: "); int id = Integer.parseInt(sc.nextLine());
        Consulta c = consultaDAO.findById(id);
        if (c == null) { System.out.println("Consulta nao encontrada."); return; }
        System.out.print("Data (" + c.getData() + "): "); String sdata = sc.nextLine(); if (!sdata.isEmpty()) c.setData(Date.valueOf(sdata));
        System.out.print("Hora (" + c.getHora() + "): "); String shora = sc.nextLine(); if (!shora.isEmpty()) c.setHora(Time.valueOf(shora));
        System.out.print("Observacoes (" + c.getObservacoes() + "): "); String obs = sc.nextLine(); if (!obs.isEmpty()) c.setObservacoes(obs);
        System.out.print("Id paciente (" + c.getIdPaciente() + "): "); String sip = sc.nextLine(); if (!sip.isEmpty()) c.setIdPaciente(Integer.parseInt(sip));
        System.out.print("Id medico (" + c.getIdMedico() + "): "); String sim = sc.nextLine(); if (!sim.isEmpty()) c.setIdMedico(Integer.parseInt(sim));
        consultaDAO.update(c);
        System.out.println("Atualizado.");
    }

    private static void deletarConsulta() throws SQLException {
        System.out.print("Id consulta: "); int id = Integer.parseInt(sc.nextLine());
        consultaDAO.delete(id);
        System.out.println("Deletado.");
    }

    private static void listarReceitas() throws SQLException {
        List<Receita> list = receitaDAO.findAll();
        for (Receita r : list) {
            System.out.println(r.getId() + " | " + r.getDescricao() + " | consultaId=" + r.getIdConsulta());
        }
    }

    private static void inserirReceita() throws SQLException {
        System.out.print("Descricao: "); String desc = sc.nextLine();
        System.out.print("Data emissao (YYYY-MM-DD): "); Date de = Date.valueOf(sc.nextLine());
        System.out.print("Validade (YYYY-MM-DD): "); Date val = Date.valueOf(sc.nextLine());
        System.out.print("Id consulta: "); int idc = Integer.parseInt(sc.nextLine());
        Receita r = new Receita(null, desc, de, val, idc);
        receitaDAO.insert(r);
        System.out.println("Inserido com id: " + r.getId());
    }

    private static void atualizarReceita() throws SQLException {
        System.out.print("Id receita: "); int id = Integer.parseInt(sc.nextLine());
        Receita r = receitaDAO.findById(id);
        if (r == null) { System.out.println("Receita nao encontrada."); return; }
        System.out.print("Descricao (" + r.getDescricao() + "): "); String desc = sc.nextLine(); if (!desc.isEmpty()) r.setDescricao(desc);
        System.out.print("Data emissao (" + r.getDataEmissao() + "): "); String sde = sc.nextLine(); if (!sde.isEmpty()) r.setDataEmissao(Date.valueOf(sde));
        System.out.print("Validade (" + r.getValidade() + "): "); String sval = sc.nextLine(); if (!sval.isEmpty()) r.setValidade(Date.valueOf(sval));
        System.out.print("Id consulta (" + r.getIdConsulta() + "): "); String sidc = sc.nextLine(); if (!sidc.isEmpty()) r.setIdConsulta(Integer.parseInt(sidc));
        receitaDAO.update(r);
        System.out.println("Atualizado.");
    }

    private static void deletarReceita() throws SQLException {
        System.out.print("Id receita: "); int id = Integer.parseInt(sc.nextLine());
        receitaDAO.delete(id);
        System.out.println("Deletado.");
    }

    private static void listarConsultasComJoin() throws SQLException {
        List<String> rows = consultaDAO.listConsultasComPacienteMedico();
        for (String r : rows) System.out.println(r);
    }

    private static void listarMedicosComEspecialidades() throws SQLException {
        List<String> rows = medicoDAO.listMedicosComEspecialidades();
        for (String r : rows) System.out.println(r);
    }

    private static void listarReceitasComPaciente() throws SQLException {
        List<String> rows = receitaDAO.listReceitasComPaciente();
        for (String r : rows) System.out.println(r);
    }
}
