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
public class Jogador extends Pessoa{
    private String pix;
    
    Scanner s = new Scanner(System.in);
    
    Jogador(){
        super();
        System.out.print("PIX: ");
        this.pix = s.nextLine();
    }
    
    public void listarDados(){
        super.listarDados();
        System.out.println("PIX: " + this.pix);

    }
}
