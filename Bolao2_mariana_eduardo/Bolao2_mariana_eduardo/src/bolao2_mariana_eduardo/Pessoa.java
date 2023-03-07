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
import java.util.Scanner;
public class Pessoa {

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }
    protected String nome;
    private String cpf;
    
    Scanner s = new Scanner(System.in);
    
    Pessoa(){
        System.out.print("Nome: ");
        this.nome = s.nextLine();
        System.out.print("CPF: ");
        this.cpf = s.nextLine();
    }
    
    public void listarDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + getCpf());
    }
}
