USE clinica_medica;

INSERT INTO paciente (nome, data_nascimento, telefone, endereco) VALUES
('Ana Silva', '1990-05-10', '11999990000', 'Rua A, 123'),
('Joao Souza', '1985-12-01', '11988880000', 'Av B, 456');

INSERT INTO medico (nome, crm, telefone, email) VALUES
('Dr. Pedro', 'CRM12345', '11977770000', 'pedro@clinica.com'),
('Dra. Carla', 'CRM54321', '11966660000', 'carla@clinica.com');

INSERT INTO especialidade (nome, descricao) VALUES
('Cardiologia','Doenca do coracao'),
('Dermatologia','Pele');

INSERT INTO medico_especialidade (id_medico, id_especialidade) VALUES
(1,1),(2,2);

INSERT INTO consulta (data, hora, observacoes, id_paciente, id_medico) VALUES
('2025-11-25','09:00:00','Retorno',1,1),
('2025-11-26','11:30:00','Primeira consulta',2,2);

INSERT INTO receita (descricao, data_emissao, validade, id_consulta) VALUES
('Paracetamol 500mg', '2025-11-25','2026-05-25',1);
