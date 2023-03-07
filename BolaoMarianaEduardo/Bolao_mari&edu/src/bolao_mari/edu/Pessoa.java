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

public class Pessoa {
    protected String nome,cpf;
    
    Pessoa(){
        Scanner s = new Scanner(System.in);
        System.out.print("Nome: ");
        this.nome = s.nextLine();
        System.out.print("CPF: ");
        this.cpf = s.nextLine();
    }
    
    public void listarDados(){
        System.out.println(this.nome);
        System.out.println(this.cpf);
    }
}
