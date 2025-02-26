import java.util.Scanner;
public class exercicio8 {
public static void main(String[]args) {

    Scanner scanner1 = new Scanner(System.in);

    System.out.print("Insira um valor para ver se ele é positivo ou negativo");
    int ValorInserido = scanner1.nextInt();

    if(ValorInserido < 0 ) {
        System.out.print("O VALOR INSERIDO É NEGATIVO");
    }
    else {
    System.out.print("O VALOR INSERIDO É POSITIVO"); }
    scanner1.close();
}

}
        