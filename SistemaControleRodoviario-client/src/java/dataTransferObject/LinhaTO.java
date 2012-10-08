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

public class LinhaTO {
   
    private Long id;
    private String descricao;
  
    private CidadeTO origem;

    private CidadeTO Destino;

    private List<RotaTO> rota;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CidadeTO getOrigem() {
        return origem;
    }

    public void setOrigem(CidadeTO origem) {
        this.origem = origem;
    }

    public CidadeTO getDestino() {
        return Destino;
    }

    public void setDestino(CidadeTO Destino) {
        this.Destino = Destino;
    }

    public List<RotaTO> getRota() {
        return rota;
    }

    public void setRota(List<RotaTO> rota) {
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
