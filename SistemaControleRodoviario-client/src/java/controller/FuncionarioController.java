/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    private InterfaceRemota ff;
    private Funcionario current;

    private InterfaceRemota getFuncionarioFacade() throws Exception {
        if (ff == null) {
            ff = (InterfaceRemota) ctx.lookup("ejb/FuncionarioFacade");
        }
        return ff;
    }

    public Funcionario getSelected() {
        if (current == null) {
            current = new Funcionario();
        }
        return current;
    }

    public String login(String nome, String senha) {
        try {
            current.setNome(nome);
            current.setSenha(senha);
            List<Funcionario> usuarioLogin = getFuncionarioFacade().verificaLogin(current);
            if (usuarioLogin.isEmpty()) {
                throw new Exception();
            } else {
                current = usuarioLogin.get(0);
                return "MenuPrincipal";
            }
        } catch (Exception e) {
            return null;
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
