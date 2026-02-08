# BibliotecaSistema (PI)

**Status:** Em desenvolvimento 🚧

## Tecnologias utilizadas
- Java (Swing / NetBeans)
- MySQL (Workbench)
- JDBC (MySQL Connector/J)
- Git e GitHub

## Time de desenvolvedores
- Victoria Dias

## Objetivo do software
Sistema desktop para gerenciamento de biblioteca, com login por perfis e controle de cadastro/consulta de clientes, livros e aluguel, respeitando permissões.

## Funcionalidades (Requisitos)
### Login e permissões
- Login com usuário e senha
- Perfis: ADMIN, ATENDENTE, BIBLIOTECARIO
- Restrições:
  - **ADMIN**: acesso administrativo
  - **ATENDENTE**: cadastrar cliente e consultar
  - **BIBLIOTECARIO**: cadastrar/editar livros, cadastrar cliente, excluir e consultar

### Clientes
- Cadastro de cliente
- Status de pagamento:
  - PENDENTE (padrão)
  - PAGO

### Livros
- Cadastro (título, gênero, quantidade)
- Consulta e busca por filtros
- Edição de quantidade conforme permissões

### Aluguéis
- Registro de aluguel (cliente + livro + quantidade)
- Status: ALUGADO / DEVOLVIDO

## Como executar
1. Criar o banco no MySQL e executar o script SQL.
2. Configurar MySQL Connector/J no NetBeans.
3. Ajustar a conexão no `ConnectionFactory`.
4. Executar o projeto a partir da tela de Login.
