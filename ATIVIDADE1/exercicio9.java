//As maçãs custam R$ 1,30 cada se forem compradas menos de uma dúzia, e R$ 1,00 se forem compradas pelo menos doze. Escreva um programa que leia o número de maçãs compradas, calcule e escreva o custo total da compra. 
import java.util.Scanner;
public class exercicio9 {

    public static void main(String[]args) {
    Scanner scanner1 = new Scanner(System.in);

    System.out.print("Insira quantas maçãs você comprou: ");
    float macasCompradas = scanner1.nextInt();
    double valorTotal = 0;
    if(macasCompradas > 12) {
     valorTotal = macasCompradas * 1;
    }
    else {
        valorTotal = macasCompradas * 1.30;
    }    
    System.out.print(valorTotal);
    scanner1.close();
}
}
