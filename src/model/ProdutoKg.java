package model;

public class ProdutoKg extends Produto {
    private double valorPorKg;
    private double pesoKg;

    public ProdutoKg(int id, String nome, String categoria, double pesoKg, double valorPorKg) {
        super(id, nome, categoria, 1); // quantidade fixa 1 para produtos a peso
        this.valorPorKg = valorPorKg;
        this.pesoKg = pesoKg;
    }

    @Override
    public double calcularTotal() {
        return pesoKg * valorPorKg;
    }

    @Override
    public String getTipo() {
        return "KG";
    }

    public double getValorPorKg() { return valorPorKg; }
    public void setValorPorKg(double valorPorKg) { this.valorPorKg = valorPorKg; }

    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | %.2f kg x R$ %.2f/kg", pesoKg, valorPorKg);
    }
}
