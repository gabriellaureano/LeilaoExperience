# 🔨 Leilão Experience - API de Gestão de Leilões

API REST para gestão de leilões online, focada no controle de ciclo de vida de itens, validação de lances em tempo real e atualização automatizada de ofertas.
Desenvolvida com **Java 17** e **Spring Boot**.

---

## 📍 Endpoints da API

**Base URL:** `/leilao`

| Categoria | Método | Endpoint | Descrição |
| :--- | :--- | :--- | :--- |
| **Usuário** | `POST` | `/usuario` | Cadastra um novo usuário no sistema |
| **Usuário** | `GET` | `/usuario` | Lista todos os usuários cadastrados |
| **Usuário** | `GET` | `/usuario/{id}` | Busca os detalhes de um usuário por ID |
| **Usuário** | `PUT` | `/usuario/{id}` | Atualiza os dados de um usuário por ID |
| **Usuário** | `DELETE` | `/usuario/{id}` | Deleta um usuário do sistema |
| **Produto** | `POST` | `/produto` | Cadastra um produto vinculado a um usuário |
| **Produto** | `GET` | `/produto` | Lista todos os produtos cadastrados |
| **Produto** | `GET` | `/produto/{id}` | Busca os detalhes de um produto por ID |
| **Produto** | `PUT` | `/produto/{id}` | Atualiza os dados de um produto por ID |
| **Produto** | `DELETE` | `/produto/{id}` | Deleta um produto do sistema |
| **Leilão** | `POST` | `/` | Cria um novo leilão com lance inicial |
| **Leilão** | `GET` | `/` | Lista todos os leilões cadastrados |
| **Leilão** | `PUT` | `/finalizar/{id}` | Finaliza um leilão e define o vencedor |
| **Leilão** | `PUT` | `/cancelar/{id}` | Cancela um leilão em andamento |
| **Lance** | `POST` | `/lance` | Realiza um novo lance em um leilão |
| **Lance** | `GET` | `/lance` | Lista o histórico global de lances |
| **Lance** | `GET` | `/lance/{id}` | Busca os lances realizados por um usuário específico |

---

## 🚀 Exemplos de Requisição

1️⃣ Cadastrar Usuário

Endpoint: POST /leilao/usuario

Payload:
{
"nome": "Gabriel Laureano",
"email": "gabriel@gmail.com",
"senha": "1234"
}

---

2️⃣ Cadastrar Produto

Endpoint: POST /leilao/produto
Vincula o item ao usuário dono do produto e define sua condição (ex: NOVO, USADO).

Payload:
{
"nome": "PlayStation 5",
"descricao": "Console seminovo com 2 controles",
"condicao": "USADO",
"usuarioId": 1
}

---

3️⃣ Criar um Novo Leilão

Endpoint: POST /leilao
Apenas produtos e usuários previamente cadastrados podem ser utilizados.

Payload:
{
"produtoId": 1,
"usuarioCriadorId": 1,
"lanceInicial": 5000.00
}

---

4️⃣ Realizar um Lance

Endpoint: POST /leilao/lance
O valor do lance deve ser estritamente maior que o lance atual e o leilão deve estar ativo.

Payload:
{
"usuarioId": 2,
"leilaoId": 1,
"valor": 5500.00
}

---

5️⃣ Finalizar Leilão

Endpoint: PUT /leilao/finalizar/1
Altera o status do leilão para FINALIZADO e atribui o produto ao usuário com a maior oferta.

---

## ⚠️ Tratamento de Erros e Exceções

A API possui validações rigorosas de regras de negócio e utiliza tratamento global de exceções para garantir respostas padronizadas e amigáveis:

- Leilão Inativo: Operações de lance, cancelamento ou finalização em leilões que não estejam no status EM_ANDAMENTO lançam exceção (LeilaoEmAndamentoException).
- Validação de Valor: Lances com valores iguais ou inferiores ao maior lance atual são recusados.
- Inexistência de Recurso: Tentativas de acesso a IDs inexistentes de Usuário, Produto ou Leilão disparam exceções customizadas tratadas pelo sistema.

---

## 🧠 Lógica e Fundamentos

🔄 Atualização Automatizada de Oferta (RestClient)
Ao receber um novo lance válido no LanceService, o sistema processa a oferta e dispara uma requisição HTTP interna automatizada via RestClient para o endpoint do leilão, atualizando em tempo real o valor da maior oferta e o usuário líder.

🛡️ Controle Rígido de Estado
O ciclo de vida do leilão é gerenciado pelo Enum StatusLeilao (EM_ANDAMENTO, FINALIZADO, CANCELADO). Operações críticas só são executadas mediante a verificação do estado ativo do leilão.

📐 Arquitetura em Camadas
Aplicação desacoplada seguindo as melhores práticas de mercado:
- Controller: Exposição dos endpoints REST e validação de payloads.
- Service: Encapsulamento de todas as regras e validações de negócio.
- DTO (Data Transfer Object): Isolamento total entre as entidades do banco de dados e a camada externa via Java Records.
- Repository: Abstração do acesso a dados via Spring Data JPA.

---

## 🛠️ Como Testar o Projeto

O repositório conta com arquivos de requisição nativos (.http) na raiz do projeto para serem executados diretamente no IntelliJ IDEA ou no VS Code com a extensão HTTP Client:

- usuario.http
- produto.http
- leilao.http
- lance.http

---

## 👨‍💻 Autor

Desenvolvido por **Gabriel Laureano**