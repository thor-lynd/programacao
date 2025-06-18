import java.util.Scanner;
//Uma revendedora de carros usados paga a seus funcionários vendedores um salário fixo por mês, mais uma comissão também fixa para cada carro vendido e mais 5% do valor das vendas por ele efetuadas. Escrever um algoritmo que leia o número de carros por ele vendidos, o valor total de suas vendas, o salário fixo e o valor que ele recebe por carro vendido. Calcule e escreva o salário final do vendedor.


public class exercicio5 {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);

        System.out.print("Quantos carros foram vendidos?: ");
        int CarrosVendidos = scanner1.nextInt();
        System.out.print("Qual foi o valor total de sua venda em reais?: ");
        int ValorVendas = scanner1.nextInt();
        System.out.print("Qual o seu salário fixo?: ");
        int SalarioFixo = scanner1.nextInt();
        System.out.print("Quanto você recebe por carros vendidos?: ");
        int TaxaVendidos = scanner1.nextInt();

        int SalarioCarros = SalarioFixo + (CarrosVendidos * TaxaVendidos);
        int PorcentagemVendas = ValorVendas / 100 * 5;
        float ValorTotal = SalarioCarros + PorcentagemVendas;
        System.out.println(ValorTotal);
        scanner1.close();



    }
}