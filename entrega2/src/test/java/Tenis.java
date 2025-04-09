public class Tenis {
    private String marca;
    private String cor;
    private int tamanho;

    public Tenis(String marca, String cor, int tamanho) {
        this.marca = marca;
        this.cor = cor;
        this.tamanho = tamanho;
    }

    public void exibirDetalhes() {
        System.out.println("Marca: " + marca + ", Cor: " + cor + ", Tamanho: " + tamanho);
    }

    public void mudarTamanho(int novoTamanho) {
        this.tamanho = novoTamanho;
        System.out.println("O tamanho do tênis foi alterado para: " + novoTamanho);
    }

    public boolean compararTamanho(Tenis outro) {
        return this.tamanho == outro.tamanho;
    }
}
