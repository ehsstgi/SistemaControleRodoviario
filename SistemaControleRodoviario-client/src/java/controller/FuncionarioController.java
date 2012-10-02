/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.FuncionarioRemote;
import ejb.InterfaceRemota;
import entity.Funcionario;
import java.util.List;
import javax.naming.InitialContext;
import util.ContextUtil;

/**
 *
 * @author Eduardo
 */
public class FuncionarioController {

    private InitialContext ctx = ContextUtil.getInitialContext();
    private FuncionarioRemote ff;
    private Funcionario current;
    private InterfaceRemota<Funcionario> ir;

    private FuncionarioRemote getFuncionarioRemote() throws Exception {
        if (ff == null) {
            ff = (FuncionarioRemote) ctx.lookup("ejb/FuncionarioFacade");
        }
        return ff;
    }
    private InterfaceRemota<Funcionario> getFuncionarioFacade() throws Exception {
        if (ir == null) {
            ir = (InterfaceRemota<Funcionario>) ctx.lookup("ejb/FuncionarioFacade");
        }
        return ir;
    }

    public Funcionario getSelected() {
        if (current == null) {
            current = new Funcionario();
        }
        return current;
    }

    public boolean login(String nome, String senha) {
        try {
            getSelected().setNome(nome);
            getSelected().setSenha(senha);
            List<Funcionario> usuarioLogin = getFuncionarioRemote().verificaLoginFuncionario(getSelected());
            if (usuarioLogin.isEmpty()) {
                throw new Exception();
            } else {
                current = usuarioLogin.get(0);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean create(String nome, String senha) {
        try {
            current = new Funcionario();
            current.setNome(nome);
            current.setSenha(senha);
            getFuncionarioFacade().create(current);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
