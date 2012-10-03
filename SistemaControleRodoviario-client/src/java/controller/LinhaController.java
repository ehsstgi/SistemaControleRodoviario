/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.InterfaceRemota;
import entity.Cidade;
import entity.Linha;
import javax.naming.InitialContext;
import util.ContextUtil;

/**
 *
 * @author Eduardo
 */
public class LinhaController {

    private InitialContext ctx = ContextUtil.getInitialContext();
    private Linha current;
    private InterfaceRemota<Linha> interfaceRemota;
    private static LinhaController linhaController;

    private LinhaController() {
    }

    public static LinhaController getLinhaController() {
        if (linhaController == null) {
            linhaController = new LinhaController();
        }
        return linhaController;
    }

    private InterfaceRemota<Linha> getLinhaFacade() throws Exception {
        if (interfaceRemota == null) {
            interfaceRemota = (InterfaceRemota<Linha>) ctx.lookup("ejb/LinhaFacade");
        }
        return interfaceRemota;
    }

    public Linha getSelected() {
        if (current == null) {
            current = new Linha();
        }
        return current;
    }

    public boolean create(Cidade origem, Cidade destino, String descricao) {
        try {
            current = new Linha();
            current.setOrigem(origem);
            current.setDestino(destino);
            current.setDescricao(descricao);
            getLinhaFacade().create(current);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
