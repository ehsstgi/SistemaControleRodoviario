/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.InterfaceRemota;
import entity.Assento;
import javax.naming.InitialContext;
import util.ContextUtil;

/**
 *
 * @author Eduardo
 */
public class AssentoController {

    private InitialContext ctx = ContextUtil.getInitialContext();
    private Assento current;
    private InterfaceRemota<Assento> interfaceRemota;
    private static AssentoController assentoController;

    private AssentoController() {
    }

    public static AssentoController getAssentoController() {
        if (assentoController == null) {
            assentoController = new AssentoController();
        }
        return assentoController;
    }

    private InterfaceRemota<Assento> getAssentoFacade() throws Exception {
        if (interfaceRemota == null) {
            interfaceRemota = (InterfaceRemota<Assento>) ctx.lookup("ejb/AssentoFacade");
        }
        return interfaceRemota;
    }

    public Assento getSelected() {
        if (current == null) {
            current = new Assento();
        }
        return current;
    }

    public boolean create(String sNumero) {
        try {
            current = new Assento();
            current.setNumero(Integer.parseInt(sNumero));
            getAssentoFacade().create(current);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
