import java.util.Scanner;
// Escreva um algoritmo para ler uma temperatura em graus Fahrenheit, calcular e escrever o valor correspondente em graus Celsius. Observação: Para testar se a sua resposta está correta saiba que 100°C = 212°F
public class exercicio6 {

    
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);

    System.out.print("Insira quantos Fahrenheit você quer converter para Celsius:");
    int Fah = scanner1.nextInt();

    float FahDiminuido = Fah - 32;
    float Cel = FahDiminuido * 5/9;

    System.out.print (Cel);

    scanner1.close();
}
}