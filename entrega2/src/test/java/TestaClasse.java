import java.util.Scanner;

public class TestaClasse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cachorro puppy = new Cachorro("thor", "Golden", 2);
        Mangas manga1 = new Mangas("Bakemonogatari", 3, 10);
        Albums album1 = new Albums("Chronic", 12, "twikipedia");
        Flores flor1 = new Flores("Girassol", "Amarelo", 150);
        Peixes peixe1 = new Peixes("John", "Dourado", 3);
        Pessoas pessoa1 = new Pessoas("Thor", 15, "Estudante");
        Tenis tenis1 = new Tenis("Newrocks", "Branco", 41);
        Celulares celular1 = new Celulares("Samsung", "Galaxy S20 Ultra", 128);

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Interagir com Cachorro");
            System.out.println("2. Interagir com Mangas");
            System.out.println("3. Interagir com Albums");
            System.out.println("4. Interagir com Flores");
            System.out.println("5. Interagir com Peixes");
            System.out.println("6. Interagir com Pessoas");
            System.out.println("7. Interagir com Tênis");
            System.out.println("8. Interagir com Celulares");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    puppy.latir();
                    puppy.falarIdade();
                    puppy.falarRaca();
                }
                case 2 -> {
                    manga1.falarNome();
                    manga1.falarCapitulo();
                    manga1.falarNota();
                }
                case 3 -> {
                    album1.escutar();
                    album1.skippar();
                    album1.acabar();
                }
                case 4 -> {
                    flor1.florescer();
                    flor1.crescer(10);
                    flor1.murchar();
                }
                case 5 -> {
                    peixe1.nomear();
                    peixe1.especificar();
                    peixe1.contar();
                }
                case 6 -> {
                    pessoa1.falarIdade();
                    pessoa1.falarIdade();
                    pessoa1.falarOcupacao();
                }
                case 7 -> {
                    tenis1.exibirDetalhes();
                    tenis1.mudarTamanho(42);
                    tenis1.compararTamanho(new Tenis("Nike", "Preto", 42));
                }
                case 8 -> {
                    celular1.ligar();
                    celular1.mostrarInfo();
                    celular1.desligar();
                }
                case 9 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida! Escolha novamente.");
            }
        } while (choice != 9);

        scanner.close();
    }
}