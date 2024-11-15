# Linguagem de Programação I - Laboratório I

**Professor:** Vinicius Pereira Santana  
**Data:** 30 de Outubro de 2024

## Exercício 1

A orientação a objetos (OO) é um paradigma de programação que organiza o software em torno de "objetos" — representações de elementos do mundo real ou conceitos abstratos — e suas interações. Em vez de estruturar o código como uma sequência de comandos ou uma série de funções independentes, a OO modela sistemas de forma mais intuitiva e modular, facilitando a criação de software complexo, escalável e reutilizável.

Os próximos laboratórios serão desenvolvidos com base em um único projeto, seguindo o projeto do Banco que fizemos em sala.

## Objetivo

Desenvolver um sistema de banco simples em Java que permita o cadastro de usuários e operações bancárias básicas, como consulta de saldo, depósito, saque e transferência entre contas.

## Requisitos Funcionais

1. **Cadastro de Usuários**
2. **Cadastro de Contas**
3. **Operações Bancárias**
4. **Autenticação de Usuário**

### Detalhamento dos Requisitos

#### 1. Cadastro de Usuários
- Permitir o cadastro de novos usuários no sistema.
- Cada usuário deve ter um nome, CPF e senha para acesso.
- Cada usuário pode ter uma ou mais contas associadas.

#### 2. Cadastro de Contas
- Permitir a criação de contas bancárias para os usuários cadastrados.
- Cada conta deve ter um número único, um saldo inicial e estar associada a um usuário.
- As contas podem ser de tipo corrente ou poupança.

#### 3. Operações Bancárias
- **Consulta de Saldo:** Permitir ao usuário consultar o saldo de sua conta.
- **Depósito:** Permitir o depósito de uma quantia na conta, com atualização do saldo.
- **Saque:** Permitir o saque de uma quantia da conta, com atualização do saldo. O saldo não pode ser negativo.
- **Transferência:** Permitir a transferência de uma quantia de uma conta para outra. Ambas as contas devem pertencer a usuários cadastrados no sistema.

#### 4. Autenticação de Usuário
- Permitir o login de usuários com CPF e senha.
- O acesso às operações de conta deve ser restrito ao usuário logado.


