/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao_mari.edu;

/**
 *
 * @author 20211TIIMI0044
 */
import java.util.Scanner;

public class Sistema {
    private Aposta a;
    
    Sistema(){
        this.a = new Aposta();
    }
    
    public void sorteio(){
        double p;
        int[] sort;
        
        Scanner s = new Scanner(System.in);
        System.out.print("Valor do premio: ");
        p = s.nextInt();
        sort = new int[6];
        int i = 0;
        while(i < 6){
            System.out.print("Numeros sorteados: ");
            sort[i] = s.nextInt();
            while(sort[i] < 1 || sort[i] > 60){
                System.out.print("Numeros sorteados: ");
                sort[i] = s.nextInt();
            }
            for (int x = 0; x < i; x++) {
                if (sort[x] == sort[i]) {
                    System.out.println("ERRO! Numero já inserido anteriormente. Tente novamente.");
                    i--;
                }
            }
            i++;
        }
        
        if(a.vencedora(sort)){
            a.exibirPremio(p);
        }
        else{
            System.out.println("NÃO FOI DESSA VEZ, TENTE NOVAMENTE!");
        }
    }
    
}
