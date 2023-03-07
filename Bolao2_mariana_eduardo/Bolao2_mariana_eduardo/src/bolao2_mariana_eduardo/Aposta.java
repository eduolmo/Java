/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bolao2_mariana_eduardo;

/**
 *
 * @author 20211TIIMI0044
 */
import java.util.ArrayList;
import java.util.Scanner;
public class Aposta {
    private ArrayList<Integer> numeros;
    private Jogador organizador;
    private ArrayList<Jogador> jogadores;
    
    Scanner s = new Scanner(System.in);
    
    Aposta(){
        this.numeros = new ArrayList<>();
        this.jogadores = new ArrayList<>();
    }
    
    public void inserirNumeros(){
        System.out.print("Quantidade de numeros apostados(entre 6 e 15): ");
        int q = s.nextInt();
        while(q < 6 || q > 15){
            System.out.print("Quantidade de numeros apostados(entre 6 e 15): ");
            q = s.nextInt();
        }
        
        for(int i = 0; i < q; i++){
            System.out.print("Aposte um numero não repetido(entre 1 e 60): ");
            int n = s.nextInt();
            while(n < 1 || n > 60 || this.numeros.contains(n)){
                System.out.print("Aposte um numero não repetido(entre 1 e 60): ");
                n = s.nextInt();
            }
            getNumeros().add(n);
        }
    }
    
    public void inserirOrganizador(ArrayList<Jogador> jogadores2){
        int cont = 0;
        for(Jogador j : jogadores2){
            j.listarDados();
        }
        System.out.print("CPF do organizador: ");
        String cpf = s.next();
        for(Jogador k : jogadores2){
            if(cpf.equals(k.getCpf())){
                this.organizador = k;
                System.out.println("Organizador inserido!");
                cont++;
            }
        }
        if(cont == 0){
            this.organizador = jogadores2.get(0);
            System.out.println("Organizador inserido!");
        }
    }
    
    public void inserirJogadores(ArrayList<Jogador> jogadores2){
        System.out.print("Quantidade de jogadores para inserir na aposta: ");
        int j = s.nextInt();
        
        //talvez tenha que substituir o <= por <
        //for(int i = 0; i <= j; i++){
        while(this.jogadores.size() < j){
            int cont = 0;
            for(Jogador jog : jogadores2){
                jog.listarDados();
            }
            System.out.print("Insira o CPF do jogador que quer participar da aposta: ");
            String cpf2 = s.next();
            for(Jogador jj : jogadores2){
                if(cpf2.equals(jj.getCpf())){
                    this.jogadores.add(jj);
                    System.out.println("Jogador inserido!");
                    cont++;
                }
            }
            if(cont == 0){
                this.jogadores.add(jogadores2.get(0));
                System.out.println("Jogador inserido!");
            }
        }
        
    }
    
    
    public boolean vencedora(ArrayList<Integer> nums){
        int cont = 0;
        for(int e : this.getNumeros()){
            for(int s : nums){
                if(e == s){
                    cont++;
                }
            }
        }
        if(cont == 6){
            return true;
        }
        return false;
       
    }
    
    public void listarVencedores(double premio){
        double d,parte,org;
        
        d = premio * 0.1;
        
        parte = (premio - d) / (this.jogadores.size() + 1);
        org = parte + d;
        
        System.out.println("VENCEDORES:");
        this.organizador.listarDados();
        System.out.println("Premio do organizador: " + org);
        
        for(Jogador jj : this.jogadores){
            jj.listarDados();
            System.out.println("Premio: " + parte);
        }
    }           

    /**
     * @return the numeros
     */
    public ArrayList<Integer> getNumeros() {
        return numeros;
    }
        
        
}
    
    
    
    
    
    
    
    
    
    
