/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.InterfaceRemota;
import entity.Cidade;
import javax.naming.InitialContext;
import util.ContextUtil;

/**
 *
 * @author Eduardo
 */
public class CidadeController {
    private InitialContext ctx = ContextUtil.getInitialContext();
    private Cidade current;
    private InterfaceRemota<Cidade> interfaceRemota;
    private static CidadeController cidadeController;

    private CidadeController(){
        
    }
    public static CidadeController getCidadeController(){
        if (cidadeController==null){
            cidadeController= new CidadeController();
        }
        return cidadeController;
    }
    
    
    private InterfaceRemota<Cidade> getCidadeFacade() throws Exception {
        if (interfaceRemota == null) {
            interfaceRemota = (InterfaceRemota<Cidade>) ctx.lookup("ejb/CidadeFacade");
        }
        return interfaceRemota;
    }

    public Cidade getSelected() {
        if (current == null) {
            current = new Cidade();
        }
        return current;
    }

  

    public boolean create(String nome) {
        try {
            current = new Cidade();
            current.setNome(nome);
            getCidadeFacade().create(current);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
