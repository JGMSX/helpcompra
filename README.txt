# Helpcompra — Ajudante de Compras em Java

Projeto Java simples demonstrando herança, polimorfismo e CRUD via console.

# Estrutura de Pacotes

helpcompra/
   ├── src/
      ├── main/
         └── Main.java
      ├── model/
         ├── Produto.java
         ├── ProdutoUnidade.java
         └── ProdutoKg.java 
      └── service/
            └── ListaComprasService.java
   ├── README.md
   └── README.txt

# Conceitos aplicados 

   * Herança e Abstração: Uso de classe base abstrata (Produto) para definir atributos comuns e reutilizar código nas subclasses.
   * Polimorfismo: Cálculo dinâmico de preços via método calcularTotal(), que se comporta de forma específica para itens por unidade ou peso.
   * Encapsulamento: Atributos privados acessados via Getters e Setters, garantindo a integridade dos dados.
   * Collections: Uso de estruturas de dados dinâmicas para gerenciamento eficiente dos objetos em memória.

# Funcionalidades Principais:

   * CRUD Completo: Cadastro, consulta, edição e remoção de itens.
   * Lógica de Pesagem: Suporte a preços por unidade e por quilo.
   * Relatórios: Demonstrativos com subtotais por categoria e valor global.
   * Controle de Gastos: Opção para definir e monitorar um limite orçamentário.

# Requisitos Técnicos:

   * Linguagem: Java 8+
   * Ambiente: Eclipse, IntelliJ ou VS Code.
   * Interface: Terminal (Console).

# Execução:

   1. Clone o repositório e importe-o como um Projeto Java em sua IDE.
   2. Confirme se a pasta src está no Build Path.
   3. Execute a classe Main.java


#  Exemplo de saída

**Bem vindo ao HELPCOMPRA - AJUDANTE DE COMPRAS**

Você tem um limite de gastos para essa compra?
  1.   Sim, quero definir um limite
  2.   Não, só quero montar a lista

Opção: ...

**AJUDANTE DE COMPRAS**

   1 - Adicionar produto
   2 - Listar produtos
   3 - Buscar produto
   4 -  Editar produto
   5 -  Remover produto
   6 - Relatório / Totais
   7 - Concluir compra
   0 - Sair