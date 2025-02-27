//Ler dois valores (considere que não serão lidos valores iguais) e escrever o maior deles. 
import java.util.Scanner;

public class exercicio12 {
    public static void main(String[]args) {

        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Insira o primeiro valor");
        int Valor1 = scanner1.nextInt();
        System.out.print("Insira o segundo valor");
        int Valor2 = scanner1.nextInt();

        if (Valor1>Valor2) {
            System.out.print("O maior valor é : "+ Valor1);
        }
        else {
            System.out.print("O maior valor é:" + Valor2);
        }
        scanner1.close();

}
}
