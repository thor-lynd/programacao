import java.util.Scanner;

//Escreva um algoritmo para ler o salário mensal atual de um funcionário e o percentual de reajuste. Calcular e escrever o valor do novo salário. 

public class exercicio3 {
    
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);

        System.out.print("Qual seu salário?(em reais)");
        int salario = scanner1.nextInt();
        System.out.print("Qual o percentual de reajuste?");
        int reajuste = scanner1.nextInt();
        

    
    
        
        int salariofinal = salario * (1 + reajuste / 100);
        System.out.print("O seu novo salário será:" + salariofinal);
        scanner1.close();


    }
    
    
}
