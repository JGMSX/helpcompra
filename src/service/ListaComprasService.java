package service;

import model.Produto;
import model.ProdutoKg;
import model.ProdutoUnidade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListaComprasService {

    private List<Produto> lista = new ArrayList<>();
    private int proximoId = 1;
    private double limiteGastos = -1; // -1 = sem limite definido

    public void setLimiteGastos(double limite) { this.limiteGastos = limite; }
    public double getLimiteGastos() { return limiteGastos; }
    public boolean temLimite() { return limiteGastos > 0; }
    public double calcularSaldo() { return limiteGastos - calcularTotalGeral(); }
    
    private String normalizarCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) return "Geral";
        String s = categoria.trim().toLowerCase();
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    // ── CREATE ────────────────────────────────────────────
    public Produto adicionarUnidade(String nome, String categoria, int quantidade, double valorUnitario) {
    	ProdutoUnidade p = new ProdutoUnidade(proximoId++, nome, normalizarCategoria(categoria), quantidade, valorUnitario);

        lista.add(p);
        return p;
    }

    public Produto adicionarKg(String nome, String categoria, double pesoKg, double valorPorKg) {
    	ProdutoKg p = new ProdutoKg(proximoId++, nome, normalizarCategoria(categoria), pesoKg, valorPorKg);
        lista.add(p);
        return p;
    }

    // ── READ ──────────────────────────────────────────────
    public List<Produto> listarTodos() {
        return new ArrayList<>(lista);
    }

    public Optional<Produto> buscarPorId(int id) {
        return lista.stream().filter(p -> p.getId() == id).findFirst();
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        return lista.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    // ── UPDATE ────────────────────────────────────────────
    public boolean atualizarNome(int id, String novoNome) {
        Optional<Produto> opt = buscarPorId(id);
        opt.ifPresent(p -> p.setNome(novoNome));
        return opt.isPresent();
    }

    public boolean atualizarQuantidadeUnidade(int id, int novaQtd) {
        Optional<Produto> opt = buscarPorId(id);
        if (opt.isPresent() && opt.get() instanceof ProdutoUnidade) {
            opt.get().setQuantidade(novaQtd);
            return true;
        }
        return false;
    }

    public boolean atualizarPesoKg(int id, double novoPeso) {
        Optional<Produto> opt = buscarPorId(id);
        if (opt.isPresent() && opt.get() instanceof ProdutoKg) {
            ((ProdutoKg) opt.get()).setPesoKg(novoPeso);
            return true;
        }
        return false;
    }

    public boolean atualizarPreco(int id, double novoPreco) {
        Optional<Produto> opt = buscarPorId(id);
        if (opt.isPresent()) {
            if (opt.get() instanceof ProdutoUnidade) {
                ((ProdutoUnidade) opt.get()).setValorUnitario(novoPreco);
                return true;
            } else if (opt.get() instanceof ProdutoKg) {
                ((ProdutoKg) opt.get()).setValorPorKg(novoPreco);
                return true;
            }
        }
        return false;
    }

    // ── DELETE ────────────────────────────────────────────
    public boolean remover(int id) {
        return lista.removeIf(p -> p.getId() == id);
    }

    public void limparLista() {
        lista.clear();
        proximoId = 1;
    }

    // ── RELATÓRIO ─────────────────────────────────────────
    public double calcularTotalGeral() {
        return lista.stream().mapToDouble(Produto::calcularTotal).sum();
    }

    public double calcularTotalPorCategoria(String categoria) {
        return lista.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .mapToDouble(Produto::calcularTotal)
                .sum();
    }

    public List<String> listarCategorias() {
        return lista.stream()
                .map(Produto::getCategoria)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public int totalItens() {
        return lista.size();
    }
}
