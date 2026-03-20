package main;

import model.Produto;
import model.ProdutoKg;
import model.ProdutoUnidade;
import service.ListaComprasService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ListaComprasService service = new ListaComprasService();

    public static void main(String[] args) {
        menuBoasVindas();
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInt("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> menuAdicionar();
                case 2 -> menuListar();
                case 3 -> menuBuscar();
                case 4 -> menuEditar();
                case 5 -> menuRemover();
                case 6 -> menuRelatorio();
                case 7 -> menuConcluir();
                case 0 -> System.out.println("\n🛒 Até logo! Boas compras!");
                default -> System.out.println("⚠️  Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        sc.close();
    }

//  BOAS VINDAS

    private static void menuBoasVindas() {
        System.out.println("🛒🛒  Bem vindo ao HELPCOMPRA - AJUDANTE DE COMPRAS 🛒🛒");
        System.out.println("\nVocê deseja definir um limite de gastos para essa compra?");
        System.out.println("  1 - Sim, quero definir um limite");
        System.out.println("  2 - Não, só quero montar a lista");
        int op = lerInt("Opção: ");

        if (op == 1) {
            double limite = lerDouble("Qual o limite? R$ ");
            service.setLimiteGastos(limite);
            System.out.printf(" Limite de R$ %.2f definido!%n", limite);
        } else {
            System.out.println("Ok! Sem limite definido.");
        }
    }

//  MENU PRINCIPAL

    private static void exibirMenuPrincipal() {
    System.out.println(
        "\n Bem vindo ao AJUDANTE DE COMPRAS. Digite uma opção: \n\n" +
        "1 - Adicionar produto\n" +
        "2 - Listar produtos\n" +
        "3 - Buscar produto\n" +
        "4 - Editar produto\n" +
        "5 - Remover produto\n" +
        "6 - Relatório / Totais\n" +
        "7 - Concluir compra\n" +
        "0 - Sair\n"
    );
}

//  ADICIONAR

    private static void menuAdicionar() {
        System.out.println("\n ADICIONAR PRODUTO");
        System.out.println("  1 - Por UNIDADE (ex: caixa de leite, sabão)");
        System.out.println("  2 - Por KG      (ex: carne, fruta, legume)");
        System.out.print("Tipo: ");
        int tipo = lerInt("");

        System.out.print("Nome do produto : ");
        String nome = sc.nextLine().trim();

        System.out.print("Categoria       : ");
        String cat = sc.nextLine().trim();

        if (tipo == 1) {
            int qtd = lerInt("Quantidade (un) : ");
            double preco = lerDouble("Preço por unidade (R$): ");
            Produto p = service.adicionarUnidade(nome, cat, qtd, preco);
            System.out.printf("✅ Produto adicionado! Total: R$ %.2f%n", p.calcularTotal());

        } else if (tipo == 2) {
            double peso = lerDouble("Peso (kg)       : ");
            double totalPago = lerDouble("Valor total pago (R$): ");
            double precoPorKg = totalPago / peso;
            Produto p = service.adicionarKg(nome, cat, peso, precoPorKg);
            System.out.printf("✅ Produto adicionado! Total: R$ %.2f%n", p.calcularTotal());

        } else {
            System.out.println("⚠️  Tipo inválido.");
        }
    }

//  LISTAR

    private static void menuListar() {
        System.out.println("\nLISTA DE COMPRAS");
        List<Produto> produtos = service.listarTodos();

        if (produtos.isEmpty()) {
            System.out.println("Que pena! A lista está vazia :( ");
            return;
        }

        System.out.printf("%-4s %-20s %-15s %-5s %-6s %-10s  Detalhe%n",
                "ID", "Nome", "Categoria", "Qtd", "Tipo", "Total");
        System.out.println("─".repeat(100));

        for (Produto p : produtos) {
            System.out.println(p);
        }

        System.out.println("─".repeat(100));
        System.out.printf("  %d item(s) na lista  |  TOTAL GERAL: R$ %.2f%n",
                service.totalItens(), service.calcularTotalGeral());
    }

//  BUSCAR

    private static void menuBuscar() {
        System.out.println("\n BUSCAR");
        System.out.println("  1 - Por ID");
        System.out.println("  2 - Por Categoria");
        int op = lerInt("Opção: ");

        if (op == 1) {
            int id = lerInt("ID do produto: ");
            Optional<Produto> opt = service.buscarPorId(id);
            if (opt.isPresent()) {
                System.out.println("\n✅ Produto encontrado:");
                System.out.println(opt.get());
            } else {
                System.out.println("❌ Produto não encontrado.");
            }

        } else if (op == 2) {
            System.out.print("Categoria: ");
            String cat = sc.nextLine().trim();
            List<Produto> resultado = service.buscarPorCategoria(cat);
            if (resultado.isEmpty()) {
                System.out.println("❌ Nenhum produto nessa categoria.");
            } else {
                resultado.forEach(System.out::println);
                System.out.printf("  Subtotal [%s]: R$ %.2f%n", cat, service.calcularTotalPorCategoria(cat));
            }
        }
    }


//  EDITAR

    private static void menuEditar() {
        System.out.println("\n EDITAR PRODUTO");
        int id = lerInt("ID do produto a editar: ");
        Optional<Produto> opt = service.buscarPorId(id);

        if (opt.isEmpty()) {
            System.out.println("❌ Produto não encontrado.");
            return;
        }

        Produto p = opt.get();
        System.out.println("Produto atual: " + p);
        System.out.println("\n O que deseja alterar?");
        System.out.println("  1 - Nome");
        System.out.println("  2 - Preço");

        if (p instanceof ProdutoUnidade) {
            System.out.println("  3 - Quantidade");
        } else if (p instanceof ProdutoKg) {
            System.out.println("  3 - Peso (kg)");
        }

        int op = lerInt("Opção: ");
        switch (op) {
            case 1 -> {
                System.out.print("Novo nome: ");
                String nome = sc.nextLine().trim();
                service.atualizarNome(id, nome);
                System.out.println("✅ Nome atualizado.");
            }
            case 2 -> {
                double preco = lerDouble("Novo preço: R$ ");
                service.atualizarPreco(id, preco);
                System.out.println("✅ Preço atualizado.");
            }
            case 3 -> {
                if (p instanceof ProdutoUnidade) {
                    int qtd = lerInt("Nova quantidade: ");
                    service.atualizarQuantidadeUnidade(id, qtd);
                    System.out.println("✅ Quantidade atualizada.");
                } else {
                    double peso = lerDouble("Novo peso (kg): ");
                    service.atualizarPesoKg(id, peso);
                    System.out.println("✅ Peso atualizado.");
                }
            }
            default -> System.out.println("⚠️  Opção inválida.");
        }

        service.buscarPorId(id).ifPresent(prod ->
                System.out.printf("  Novo total do item: R$ %.2f%n", prod.calcularTotal()));
    }


//  REMOVER

    private static void menuRemover() {
        System.out.println("\nREMOVER PRODUTO");
        System.out.println("  1 - Remover por ID");
        System.out.println("  2 - Limpar toda a lista");
        int op = lerInt("Opção: ");

        if (op == 1) {
            int id = lerInt("ID do produto: ");
            boolean ok = service.remover(id);
            System.out.println(ok ? "✅ Produto removido." : "❌ Produto não encontrado.");
        } else if (op == 2) {
            System.out.print("Confirma limpar a lista? (s/n): ");
            String conf = sc.nextLine().trim();
            if (conf.equalsIgnoreCase("s")) {
                service.limparLista();
                System.out.println("✅ Lista limpa.");
            } else {
                System.out.println("Operação cancelada.");
            }
        }
    }


//  RELATÓRIO

    private static void menuRelatorio() {
        System.out.println("\nRELATÓRIO DE COMPRAS");

        if (service.totalItens() == 0) {
            System.out.println("Que pena! A lista está vazia :(");
            return;
        }

        List<String> categorias = service.listarCategorias();

        System.out.printf("%-20s | %s%n", "CATEGORIA", "SUBTOTAL");
        System.out.println("─".repeat(40));
        for (String cat : categorias) {
            System.out.printf("%-20s | R$ %8.2f%n", cat, service.calcularTotalPorCategoria(cat));
        }
        System.out.println("─".repeat(40));
        System.out.printf("%-20s | R$ %8.2f%n", "TOTAL GERAL", service.calcularTotalGeral());

        if (service.temLimite()) {
            double saldo = service.calcularSaldo();
            System.out.printf("%-20s | R$ %8.2f%n", "Limite", service.getLimiteGastos());
            if (saldo >= 0) {
                System.out.printf("%-20s | R$ %8.2f ✅%n", "Saldo", saldo);
            } else {
                System.out.printf("%-20s | R$ %8.2f ⚠️  limite ultrapassado!%n", "Saldo", saldo);
            }
        }

        System.out.printf("%nTotal de itens na lista: %d%n", service.totalItens());
    }
    
//  CONCLUIR COMPRA

    private static void menuConcluir() {
    	List<Produto> produtos = service.listarTodos();

    if (produtos.isEmpty()) {
        System.out.println("Que pena! A lista está vazia :(");
        return;
    }

    System.out.println("\n── COMPRA DE HOJE FINALIZADA ──");
    System.out.printf("%-20s | %-10s | %s%n", "Produto", "Qtd / Kg", "Total");
    System.out.println("─".repeat(50));

    for (Produto p : produtos) {
        String qtdOuKg;
        if (p instanceof ProdutoKg) {
            qtdOuKg = String.format("%.2f kg", ((ProdutoKg) p).getPesoKg());
        } else {
            qtdOuKg = String.format("%d un", p.getQuantidade());
        }
        System.out.printf("%-20s | %-10s | R$ %.2f%n", p.getNome(), qtdOuKg, p.calcularTotal());
    }

    System.out.println("─".repeat(50));
    System.out.printf("%-20s | %-10s | R$ %.2f%n", "TOTAL", "", service.calcularTotalGeral());
}

    //  HELPERS DE LEITURA

    private static int lerInt(String msg) {
        while (true) {
            System.out.print(msg);
            String linha = sc.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Digite um número válido.");
            }
        }
    }

    private static double lerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            String linha = sc.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Digite um valor numérico válido.");
            }
        }
    }
}
