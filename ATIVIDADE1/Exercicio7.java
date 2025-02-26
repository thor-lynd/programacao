import java.util.Scanner;
//Ler um valor e escrever a mensagem É MAIOR QUE 10! se o valor lido for maior que 10, caso contrário escrever NÃO É MAIOR QUE 10! 
public class Exercicio7 {
}

public static void main(String[]args) { 
    Scanner scanner1 = new Scanner(System.in);
    System.out.print("Insira um valor: ");
    int Valor = scanner1.nextInt();
     
    if (Valor > 10) {
        System.out.print("É MAIOR QUE 10!");
    }
    else{
     System.out.print("É MENOR QUE 10!");
    }

    scanner1.close();


}