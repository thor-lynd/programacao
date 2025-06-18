
//Ler o ano atual e o ano de nascimento de uma pessoa. Escrever uma mensagem que diga se ela poderá ou não votar este ano (não é necessário considerar o mês em que a pessoa nasceu).
import java.util.Scanner;
public class exercicio11 {
    
    public static void main(String[]args) {
        Scanner scanner1 = new Scanner(System.in);

        System.out.print("Insira o ano que estamos.");
        int anoAtual = scanner1.nextInt();
        
        System.out.print("Insira o ano que você nasceu:");
        int anoNasc = scanner1.nextInt();

        int idade = anoAtual - anoNasc;

        if (idade < 16) {
            System.out.print("Você não pode votar.");
        }
        else {
            System.out.print("Você pode votar.");
        }
        scanner1.close();
    

    
}
}