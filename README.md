# Banco1000 - Projeto de Laboratório

## Descrição do Projeto

Este projeto foi desenvolvido como parte das atividades da disciplina **Linguagem de Programação II - Laboratório I**, ministrada pelo professor Vinicius Pereira Santana. O objetivo é criar o sistema **Banco1000** em Java, aplicando conceitos de orientação a objetos (OO), permitindo a gestão de contas bancárias e operações financeiras básicas.

## Exercício 1

### 0.1 Objetivos

A versão 2.0 do sistema de banco em Java introduz novas funcionalidades e conceitos, incluindo o suporte para múltiplas agências, gerenciamento avançado de contas com herança, tratamento de exceções específicas e persistência de dados em arquivos.

### 0.2 Requisitos Funcionais

**Funcionalidades Adicionadas na Versão 2.0**

a) Cada agência pode gerenciar diversas contas. As contas são registradas em agências específicas.

b) Contas herdando características e comportamentos da classe base Conta. Adição de novos tipos de contas, como:
   - **ContaCorrente:** Taxas de manutenção.
   - **ContaPoupança:** Rendimento mensal.
   - **ContaSalário:** Restrita a depósitos do empregador e limitada a um número fixo de saques.

c) Novas exceções personalizadas para tratar erros no contexto de múltiplas agências e contas.

d) Salvar e carregar os dados das agências, contas e transações a partir de arquivos no formato .csv.

## Funcionalidades Implementadas

### 1. Cadastro de Usuários

- Cadastro de novos usuários com nome, CPF e senha.
- Associação de múltiplas contas a um usuário.

### 2. Cadastro de Contas

- Criação de contas bancárias para usuários cadastrados.
- Cada conta possui um número único e saldo inicial.
- Suporte a contas corrente e poupança.

### 3. Operações Bancárias

- **Consulta de Saldo:** Visualização do saldo da conta.
- **Depósito:** Adicionar valores ao saldo da conta.
- **Saque:** Retirada de valores com controle de saldo negativo.
- **Transferência:** Transferência de valores entre contas do sistema.

### 4. Autenticação de Usuário

- Login seguro via CPF e senha.
- Restrição de acesso às contas apenas ao usuário autenticado.

## Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework:** Spring Boot
- **Armazenamento de Dados:** Arquivos CSV utilizando Apache Commons CSV
- **Ferramenta de Build:** Maven
- **Controle de Versão:** Git/GitHub

## Como Executar o Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/jonas07br/Banco1000-Java.git
   cd Banco1000-Java
   ```

2. Compile o projeto usando Maven:

   ```bash
   mvn clean install
   ```

3. Execute o projeto:

   ```bash
   java -jar target/banco1000-1.0-SNAPSHOT.jar
   ```

## Possíveis Melhorias Futuras

- Implementação de uma interface gráfica.
- Integração com APIs de serviços bancários.
- Auditoria e relatórios detalhados de transações.

## Autores

- Jonas Rafael Silva Cavalcanti
- Gabriel Guilherme Carvalho Viana
- Matheus Gabriel Souto de Lira Freitas

---

Projeto desenvolvido para fins acadêmicos.

