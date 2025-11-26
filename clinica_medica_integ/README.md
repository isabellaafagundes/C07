# clinica_medica_integration

Projeto de integração Java + MySQL para a disciplina — entrega 1.

## Como usar

1. Ajuste `src/main/java/br/clinica/db/DBConnection.java` com sua URL, usuário e senha do MySQL.
2. Crie o schema no MySQL usando `sql/clinica_schema.sql` (seu script) e depois rode `sql/inserts_example.sql` para popular dados iniciais.
3. Build com Maven:
   - `mvn clean package`
4. Rode:
   - `java -cp target/clinica_medica_integration-1.0-SNAPSHOT.jar;~/.m2/repository/mysql/mysql-connector-j/8.1.0/mysql-connector-j-8.1.0.jar br.clinica.app.Main`
   (ou use sua IDE e adicione o connector ao classpath)

## O que está incluso
- 5 modelos (Paciente, Medico, Especialidade, Consulta, Receita)
- 5 DAOs completos com CRUD (INSERT, UPDATE, DELETE, SELECT)
- `Main` com menu CLI para todas operações e opções de JOINs solicitados.
