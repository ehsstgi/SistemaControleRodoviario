/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import controller.PassagemController;
import pagamento.Cartao;

/**
 *
 * @author Eduardo
 */
@Named("cartaoController")
@RequestScoped
public class CartaoController implements Serializable {

    private Cartao current;
    @Inject
    private PassagemController passagemController;

    public Cartao getSelected() {
        if (current == null) {
            current = new Cartao();
        }
        return current;
    }
    
    public String confirmaCompra(){
        return passagemController.create();
    }
}
