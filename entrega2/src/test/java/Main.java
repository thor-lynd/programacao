public class Main {
    public static void main(String[] args) {
        Cachorro puppy = new Cachorro("thor", "Golden", 2);
        Mangas manga1 = new Mangas("Bakemonogatari", 3, 10);
        Albums album1 = new Albums("Chronic", 12, "twikipedia");
        Flores flor1 = new Flores("Girassol", "Amarelo", 150);
        Peixes peixe1 = new Peixes("John", "Dourado", 3);
        Pessoas pessoa1 = new Pessoas("Thor", 15, "Estudante");
        Tenis tenis1 = new Tenis("Newrocks", "Branco", 41);
        Celulares celular1 = new Celulares("Samsung", "Galaxy S20 Ultra", 128);
        puppy.latir();
        puppy.falarIdade();
        puppy.falarRaca();

        manga1.falarNome();
        manga1.falarCapitulo();
        manga1.falarNota();

        album1.escutar();
        album1.skippar();
        album1.acabar();
        
        flor1.florescer();
        flor1.crescer(10);
        flor1.murchar();
        
        peixe1.nomear();
        peixe1.especificar();
        peixe1.contar();
        
        pessoa1.falarIdade();
        pessoa1.falarIdade();
        pessoa1.falarOcupacao();
        
        tenis1.exibirDetalhes();
        tenis1.mudarTamanho(42);
        tenis1.compararTamanho(new Tenis("Nike", "Preto", 42));

        celular1.ligar();
        celular1.mostrarInfo();
        celular1.desligar();

        
    }
}