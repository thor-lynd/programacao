import java.util.Scanner;
//Escreva um algoritmo para ler o número total de eleitores de um município, o número de votos brancos, nulos e válidos. Calcular e escrever o percentual que cada um representa em relação ao total de eleitores

public class exercicio2 {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        
    
        System.out.print("Quantos eleitores tem na cidade?: ");
        float eleitores = scanner1.nextInt();
        System.out.print("Quantos votos brancos?" );
        float brancos = scanner1.nextInt();
        System.out.print("Quantos votos nulos? ");
        float nulos = scanner1.nextInt();
        System.out.print("Quantos votos válidos?: ");
        float válidos = scanner1.nextInt(); 
        
        brancos = brancos / eleitores * 100;
        nulos = nulos / eleitores * 100;
        válidos = válidos / eleitores * 100;
        

        
        System.out.println("A porcentagem de votos brancos foi:" +  brancos +"%");
        System.out.println("A porcentagem de votos nulos foi:" + nulos + "%");
        System.out.println("A porcentagem de votos váidos foi:" + válidos + "%");

        scanner1.close();
        


    }
}
