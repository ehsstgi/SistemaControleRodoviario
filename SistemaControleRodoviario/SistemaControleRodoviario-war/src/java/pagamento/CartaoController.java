/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pagamento;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import jsf.PassagemController;

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
