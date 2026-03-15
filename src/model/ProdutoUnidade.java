package model;

public class ProdutoUnidade extends Produto {
    private double valorUnitario;

    public ProdutoUnidade(int id, String nome, String categoria, int quantidade, double valorUnitario) {
        super(id, nome, categoria, quantidade);
        this.valorUnitario = valorUnitario;
    }

    @Override
    public double calcularTotal() {
        return getQuantidade() * valorUnitario;
    }

    @Override
    public String getTipo() {
        return "UNIT";
    }

    public double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(double valorUnitario) { this.valorUnitario = valorUnitario; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Preço/un: R$ %.2f", valorUnitario);
    }
}
