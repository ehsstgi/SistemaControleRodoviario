/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTransferObject;

import java.util.List;

/**
 *
 * @author Eduardo
 */

public class FuncionarioTO {
    
    private Long id;
    private String nome;
    private String senha;
    private List<PassagemTO> passagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<PassagemTO> getPassagem() {
        return passagem;
    }

    public void setPassagem(List<PassagemTO> passagem) {
        this.passagem = passagem;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    
}
