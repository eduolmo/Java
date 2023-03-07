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

public class Jogador extends Pessoa{
    private String pix;
    
    Jogador(){
        super();
        Scanner s = new Scanner(System.in);
        System.out.print("PIX: ");
        this.pix = s.nextLine();
    }
    
    public void listarDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("PIX: " + this.pix);
    }
    
}
