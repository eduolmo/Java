/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao_mari.edu;

/**
 *
 * @author 20211tiimi0044
 */
import java.util.Scanner;

public class Aposta {
    private int n;
    private int[] numeros;
    private Jogador organizador,jog1,jog2;
    
    Aposta(){
        Scanner s = new Scanner(System.in);
        System.out.print("Quantidade de numeros apostados: ");
        this.n = s.nextInt();
        while(this.n < 6 || this.n > 15){
            System.out.println("ERRO! Digite um numero entre 6 e 15");
            System.out.print("Quantidade de numeros apostados: ");
            this.n = s.nextInt();
        }
        this.numeros = new int[n];
        int i = 0;
        while(i < n){
            System.out.print("Numeros escolhidos: ");
            numeros[i] = s.nextInt();
            while(numeros[i] > 60 || numeros[i] < 1){
                System.out.println("ERRO! Digite um numero entre 1 e 60");
                System.out.print("Numeros escolhidos: ");
                numeros[i] = s.nextInt();
            }
            for (int x = 0; x < i; x++) {
                if (numeros[x] == numeros[i]) {
                    System.out.println("ERRO! Numero já inserido anteriormente. Tente novamente.");
                    i--;
                }
            }
            i++;
        }
        this.organizador = new Jogador();
        this.jog1 = new Jogador();
        this.jog2 = new Jogador();
    }
    
    public boolean vencedora(int[] nums){
        int cont = 0;
        for(int i = 0; i < numeros.length; i++){
            for(int j = 0; j < nums.length; j++){
                if(numeros[i] == nums[j]){
                    cont++;
                }
            }
        }
        if(cont == 6){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void exibirPremio(double valor){
        double d,parte,org;
        
        d = valor * 0.1;
        parte = (valor - d) / 3;
        org = parte + d;
        
        System.out.println("Vencedores:");
        organizador.listarDados();
        System.out.println("Premio: " + org);
        jog1.listarDados();
        System.out.println("Premio: " + parte);
        jog2.listarDados();
        System.out.println("Premio: " + parte);
    }
    
}
