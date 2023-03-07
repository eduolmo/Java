/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao2_mariana_eduardo;

/**
 *
 * @author 20211tiimi0044
 */
import java.util.Scanner;
public class Bolao2_mariana_eduardo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);
        
        Sistema sist = new Sistema();
        
        System.out.println("1.Cadastrar  jogador");
        System.out.println("2.Cadastrar aposta");
        System.out.println("3.Inserir sorteio e listar vencedores");
        System.out.println("4.Sair");
        int r = s.nextInt();
        
        while(r != 4){
            if(r == 1){
                sist.cadastrarJogador();
            }
            
            if(r == 2){
                sist.cadastrarAposta();
            }
            
            if(r == 3){
                sist.inserirSorteio();
            }
            
            if(r < 1 || r > 4){
                System.out.println("Insira um numero de 1 a 4");
            }
            
            System.out.println("1.Cadastrar  jogador");
            System.out.println("2.Cadastrar aposta");
            System.out.println("3.Inserir sorteio e listar vencedores");
            System.out.println("4.Sair");
            r = s.nextInt();
            
        }
         
        System.out.println("SAIU!");
        
    }
    
}
