/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTransferObject;

/**
 *
 * @author Eduardo
 */

public class OnibusTO {
  
    private Long id;
    private String descricao;
 
    private RotaTO rota;
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public RotaTO getRota() {
        return rota;
    }

    public void setRota(RotaTO rota) {
        this.rota = rota;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    
}
