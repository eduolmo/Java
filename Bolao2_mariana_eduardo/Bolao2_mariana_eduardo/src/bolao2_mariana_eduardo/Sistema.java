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
import java.util.ArrayList;
import java.util.Scanner;
public class Sistema {
    private ArrayList<Aposta> apostas;
    private ArrayList<Jogador> jogadores;
    
    Scanner s = new Scanner(System.in);
    
    Sistema(){
        this.apostas = new ArrayList<Aposta>();
        this.jogadores = new ArrayList<Jogador>();
    }
    
    public void cadastrarJogador(){
        this.jogadores.add(new Jogador());
    }
    
    public void cadastrarAposta(){
        Aposta a = new Aposta();
        a.inserirNumeros();
        a.inserirOrganizador(this.jogadores);
        a.inserirJogadores(this.jogadores);
        this.apostas.add(a);
    }
    
    private ArrayList<Aposta> vencedoras(ArrayList<Integer> nums){
        ArrayList<Aposta> ganharam = new ArrayList<>();
        for(Aposta a : this.apostas){
            if(a.vencedora(nums)){
                ganharam.add(a);
            }
        }
        return ganharam;
        
    }
    
    public void inserirSorteio(){
        ArrayList<Integer> sorteados = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            System.out.print("Numero sorteado: ");
            int sort = s.nextInt();
            while(sort < 1 || sort > 60 || sorteados.contains(sort)){
                System.out.print("Numero sorteado: ");
                sort = s.nextInt();
            }
            sorteados.add(sort);
        }
        System.out.print("Premio: ");
        double premio = s.nextInt();
        
        ArrayList<Aposta> ganhadoras = vencedoras(sorteados);
        
        double divisao = premio / ganhadoras.size();
        
        for(Aposta aa : ganhadoras){
            aa.listarVencedores(divisao);
        }
        
    }
    
    
    
}
