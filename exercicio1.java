import java.util.Scanner;

public class exercicio1{
    
    public static void main(String[] args) {
    
        Scanner scanner1 = new Scanner(System.in);

        
        int resultado = 0; 

        System.out.print("Quantos anos você tem?: ");
        int anos = scanner1.nextInt();
        System.out.print("Quantos meses você tem:? ");
        int meses = scanner1.nextInt(); 
        System.out.print("Quantos dias você tem?");
        int dias = scanner1.nextInt();
        resultado = anos * 365 + meses * 30 + dias;
        System.out.println("você tem");
        System.out.print (resultado);
        
        scanner1.close();
    }
}
