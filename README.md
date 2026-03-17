# 🐾 Sistema de Gestão de Abrigo de Pets (CLI)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![CLI](https://img.shields.io/badge/CLI-Terminal-black?style=for-the-badge)

## 💻 Sobre o Projeto
Este é um sistema de gerenciamento de adoção de pets executado via **Interface de Linha de Comando (CLI)**. O projeto foi desenvolvido como resolução do desafio de programação proposto por [@devmagro](https://github.com/devmagro), focando na aplicação de boas práticas de Orientação a Objetos e manipulação do File System utilizando Java pura.

O sistema permite que o dono de um abrigo cadastre, busque, atualize e remova pets, salvando todas as informações em arquivos `.txt` formatados automaticamente com base em datas e dados dos animais.

## 🚀 Funcionalidades Principais
- **Menu Interativo:** Navegação 100% via terminal com validação rigorosa de entradas (bloqueio de letras em campos numéricos, números negativos, etc).
- **Leitura Dinâmica:** O formulário de cadastro é gerado a partir da leitura de um arquivo base `formulario.txt` utilizando Java IO.
- **Cadastro de Pets:** Validação de regras de negócio (peso, idade, nomes sem caracteres especiais) e uso de `Enums` para Tipos e Sexo.
- **Persistência em Arquivos:** Cada pet é salvo individualmente na pasta `/petsCadastrados`, com um padrão de nomenclatura gerado dinamicamente (Ex: `20231101T1234-FLORZINHADASILVA.TXT`).
- **Busca Avançada Combinada:** Pesquisa flexível combinando até 2 critérios (ex: Nome + Idade) com leitura *case-insensitive* e destaque visual em negrito no terminal.
- **Atualização e Exclusão:** Gerenciamento completo do ciclo de vida dos registros (CRUD) direto nos arquivos de texto.

## 🛠️ Tecnologias e Conceitos Aplicados
- **Linguagem:** Java
- **Paradigmas:** Programação Orientada a Objetos (POO)
- **Recursos Técnicos:** - `java.io` / `java.nio` (Leitura e Escrita de Arquivos)
  - Collections e Arrays
  - Tratamento de Exceções (`try-catch`, Exceções customizadas)
  - Expressões Regulares (Regex) para validações
  - Enums e Constantes

## ⚙️ Como Executar
1. Clone este repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/desafio-pet-abrigo.git](https://github.com/SEU_USUARIO/desafio-pet-abrigo.git)
