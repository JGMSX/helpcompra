# helpcompra — Ajudante de Compras em Java

Projeto Java simples para Eclipse demonstrando herança, polimorfismo e CRUD via console.

## Estrutura de Pacotes
```
helpcompra/
└── src/
    ├── main/
    │   └── Main.java             ← Ponto de entrada + menus
    ├── model/
    │   ├── Produto.java          ← Classe ABSTRATA base (herança)
    │   ├── ProdutoUnidade.java   ← Herda Produto — vendido por unidade
    │   └── ProdutoKg.java        ← Herda Produto — vendido por kg
    └── service/
        └── ListaComprasService.java ← CRUD da lista de compras
```

## Conceitos de POO aplicados

| Conceito          | Onde está                                               |
|-------------------|---------------------------------------------------------|
| **Herança**       | `ProdutoUnidade` e `ProdutoKg` herdam de `Produto`      |
| **Abstrato**      | `Produto` é `abstract`; método `calcularTotal()` também |
| **Polimorfismo**  | Lista de `Produto` chama `calcularTotal()` de cada tipo |
| **Encapsulamento**| Atributos `private` com getters/setters                 |

## Como importar no Eclipse

1. File → New → Java Project
2. Nome do projeto: `helpcompra`
3. Desmarque **"Use default location"** e aponte para a pasta extraída
   *(ou crie o projeto em branco e crie os pacotes/classes manualmente)*
4. Crie os pacotes:
   - `main`
   - `model`
   - `service`
5. Crie cada `.java` dentro do pacote correto e cole o código
6. Clique com botão direito em `Main.java` → **Run As → Java Application**

## 📋 Funcionalidades

- ➕ **Adicionar** produto por unidade ou por kg
- 📋 **Listar** todos os produtos com total por item
- 🔍 **Buscar** por ID ou por categoria
- ✏️ **Editar** nome, preço, quantidade ou peso
- 🗑️ **Remover** por ID ou limpar toda a lista
- 📊 **Relatório** com subtotal por categoria e total geral
- ✅ **Concluir compra** com tabela resumo do dia

## 💡 Exemplo de saída
```
🛒🛒  Bem vindo ao HELPCOMPRA - AJUDANTE DE COMPRAS 🛒🛒

Você tem um limite de gastos para essa compra?
  1 - Sim, quero definir um limite
  2 - Não, só quero montar a lista
Opção:

🛒🛒🛒  AJUDANTE DE COMPRAS

1 - ➕  Adicionar produto
2 - 📋  Listar produtos
3 - 🔍  Buscar produto
4 - ✏️   Editar produto
5 - 🗑️   Remover produto
6 - 📊  Relatório / Totais
7 - ✅  Concluir compra
0 - 🚪  Sair
```