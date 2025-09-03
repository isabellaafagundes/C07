# 🏥 Sistema de Gestão de Clínica Médica

## 1. Introdução
O presente projeto tem como objetivo a elaboração de um modelo conceitual e lógico para o gerenciamento de dados em uma clínica médica. A proposta contempla a definição de entidades, atributos e relacionamentos, assegurando a integridade e consistência das informações.

## 2. Escopo
O sistema visa atender às seguintes necessidades:

- Armazenamento de informações de pacientes e médicos.
- Registro de consultas médicas realizadas.
- Emissão de receitas médicas vinculadas a consultas.
- Associação de médicos às suas respectivas especialidades.

## 3. Entidades e Atributos
As entidades identificadas no modelo são:

- **Paciente**: id_paciente (PK), nome, data_nascimento, telefone, endereco
- **Médico**: id_medico (PK), nome, crm, telefone, email
- **Especialidade**: id_especialidade (PK), nome, descricao
- **Consulta**: id_consulta (PK), data, hora, observacoes
- **Receita**: id_receita (PK), descricao, data_emissao, validade

## 4. Relacionamentos
Foram definidos os seguintes relacionamentos entre as entidades:

- Paciente (1) —— (N) Consulta: um paciente pode realizar diversas consultas.
- Médico (1) —— (N) Consulta: um médico pode atender diversas consultas.
- Consulta (1) —— (1) Receita: cada consulta pode gerar, no máximo, uma receita.
- Médico (N) —— (N) Especialidade: um médico pode possuir várias especialidades e uma especialidade pode estar associada a vários médicos.

## 5. Diagrama Entidade-Relacionamento (DER)
```mermaid
erDiagram
    PACIENTE ||--o{ CONSULTA : "realiza"
    MEDICO ||--o{ CONSULTA : "atende"
    CONSULTA ||--|| RECEITA : "gera"
    MEDICO ||--o{ ESPECIALIDADE : "possui"

    PACIENTE {
        int id_paciente PK
        string nome
        date data_nascimento
        string telefone
        string endereco
    }

    MEDICO {
        int id_medico PK
        string nome
        string crm
        string telefone
        string email
    }

    ESPECIALIDADE {
        int id_especialidade PK
        string nome
        string descricao
    }

    CONSULTA {
        int id_consulta PK
        date data
        string hora
        string observacoes
    }

    RECEITA {
        int id_receita PK
        string descricao
        date data_emissao
        date validade
    }

```

## 6. Tecnologias Utilizadas 
- Linguagem de modelagem: DER (Diagrama Entidade-Relacionamento)
-  Ferramenta de documentação: Markdown
-  Ferramenta de versionamento: Git e GitHub
-  Representação gráfica: Mermaid

## 7. Conclusão 
O modelo proposto possibilita a organização estruturada das informações da clínica médica, assegurando clareza na relação entre entidades e a rastreabilidade dos dados. A modelagem atende ao requisito mínimo de cinco entidades e contempla os três tipos de relacionamentos solicitados: 1:1, 1:N e N:M. 

## 8. Autores 
Isabella Santos Fagundes
