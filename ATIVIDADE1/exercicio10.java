// Ler as notas da 1a. e 2a. avaliações de um aluno. Calcular a média aritmética simples e escrever uma mensagem que diga se o aluno foi ou não aprovado (considerar que nota igual ou maior que 6 o aluno é aprovado). Escrever também a média calculada. 
import java.util.Scanner;
public class exercicio10 {

    public static void main(String[]args) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Qual sua nota da primeira prova?:");
        int notaP1 = scanner1.nextInt();
        System.out.print("Qual a nota da segunda prova?:");
        int notaP2 = scanner1.nextInt();


        int media = (notaP1 + notaP2)/2;

        if (media >=6 )  {
            System.out.print("Você passou");
        }
        else  { 
            System.out.print("Voce nâo passou.");
        }
        scanner1.close();

        
    
}
}