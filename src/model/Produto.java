package model;

public abstract class Produto {
    private int id;
    private String nome;
    private String categoria;
    private int quantidade;

    public Produto(int id, String nome, String categoria, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    // Método abstrato que cada subclasse implementa
    public abstract double calcularTotal();

    public abstract String getTipo();

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return String.format("[%d] %-20s | Categoria: %-15s | Qtd: %-4d | Tipo: %-6s | Total: R$ %.2f",
                id, nome, categoria, quantidade, getTipo(), calcularTotal());
    }
}
