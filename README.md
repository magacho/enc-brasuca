# NanoLink - Encurtador de URLs

## Descrição do Projeto
Este projeto é o MVP (Produto Mínimo Viável) de um encurtador de URLs, denominado NanoLink. Seu objetivo é fornecer uma solução simples e eficiente para transformar URLs longas em links curtos e rastreáveis.

## Funcionalidades Principais (MVP)
*   **Encurtamento de URLs:** Recebe uma URL longa e gera um código curto único de 6 caracteres.
*   **Redirecionamento:** Redireciona usuários do NanoLink curto para a URL original.
*   **Contabilização de Acessos:** Registra o número de vezes que cada NanoLink é acessado.

## Agentes Envolvidos
O desenvolvimento deste projeto é orquestrado por um conjunto de agentes especializados, conforme definido no `GEMINI.md`:
*   **@Architect:** Define a stack tecnológica e a arquitetura do sistema.
*   **@Product:** Define requisitos, regras de negócio e critérios de aceite.
*   **@DevOps:** Responsável pela infraestrutura, CI/CD, e scripts de automação.
*   **@Dev:** Implementa a funcionalidade e os testes unitários.
*   **@QA:** Cria cenários de teste e identifica bugs.

## Estrutura do Projeto (Padrão Maven)
```
.
├── GEMINI.md                               # Documentação do fluxo de trabalho dos agentes AI
├── README.md                               # Visão geral do projeto
├── pom.xml                                 # Arquivo de configuração Maven (Project Object Model)
└── src/
    ├── main/
    │   ├── java/                           # Código-fonte principal em Java
    │   │   └── com/
    │   │       └── nanolink/               # Pacote base para a aplicação
    │   └── resources/                      # Recursos da aplicação (ex: application.properties)
    └── test/
        └── java/                           # Código-fonte de testes em Java
            └── com/
                └── nanolink/               # Pacote base para os testes
```

## Tecnologias e Bibliotecas
*   **Linguagem:** Java
*   **Ferramenta de Build:** Apache Maven
*   **Framework Backend:** Spring Boot
*   **Banco de Dados:** H2 Database (para MVP, pode ser configurado para PostgreSQL em produção)
*   **Containerização:** Docker
*   **CI/CD:** GitHub Actions
