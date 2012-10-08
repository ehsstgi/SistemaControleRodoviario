/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dataTransferObject.FuncionarioTO;
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
    private FuncionarioRemote funcionarioRemote;
    private Funcionario current;
    private InterfaceRemota<Funcionario> interfaceRemota;
    private static FuncionarioController funcionarioController;

    private FuncionarioController() {
    }

    public static FuncionarioController getFuncionarioController() {
        if (funcionarioController == null) {
            funcionarioController = new FuncionarioController();
        }
        return funcionarioController;
    }

    private FuncionarioRemote getFuncionarioRemote() throws Exception {
        if (funcionarioRemote == null) {
            funcionarioRemote = (FuncionarioRemote) ctx.lookup("ejb/FuncionarioFacade");
        }
        return funcionarioRemote;
    }

    private InterfaceRemota<Funcionario> getFuncionarioFacade() throws Exception {
        if (interfaceRemota == null) {
            interfaceRemota = (InterfaceRemota<Funcionario>) ctx.lookup("ejb/FuncionarioFacade");
        }
        return interfaceRemota;
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

    public boolean create(FuncionarioTO fTO) {
        try {
            current = dtoParaEntity(fTO);
            getFuncionarioFacade().create(current);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Funcionario dtoParaEntity(FuncionarioTO fTO) {
        Funcionario f = new Funcionario();
        f.setNome(fTO.getNome());
        f.setSenha(fTO.getSenha());
        return f;
    }
}
