import java.util.Scanner;

public class exercicio4 {
    //O custo de um carro novo ao consumidor é a soma do custo de fábrica com a porcentagem do distribuidor e dos impostos (aplicados ao custo de fábrica). Supondo que o percentual do distribuidor seja de 28% e os impostos de 45%, escrever um algoritmo para ler o custo de fábrica de um carro, calcular e escrever o custo final ao consumidor.


    public static void main(String[] args) {
        Scanner scanner1 = new Scanner( System.in);
    
        float percentualdistribuidor = 28;
        float percentualimpostos = 45;
        float impostotal = 78;

        System.out.print("Qual o custo de fábrica?");
        int custofabrica = scanner1.nextInt();

        float custodocarro = custofabrica * (1 + impostotal / 100)  ;

        System.out.print("O custo do carro será: " + custodocarro);
        scanner1.close();
    }
    
}
