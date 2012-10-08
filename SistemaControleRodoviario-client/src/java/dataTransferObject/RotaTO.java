/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTransferObject;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eduardo
 */
@Entity
public class RotaTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CidadeTO origem;
    @ManyToOne
    private CidadeTO destino;
    @Temporal(TemporalType.DATE)
    private Calendar horaOrigem;
    @Temporal(TemporalType.DATE)
    private Calendar horaDestino;
    private double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    

    public CidadeTO getOrigem() {
        return origem;
    }

    public void setOrigem(CidadeTO origem) {
        this.origem = origem;
    }

    public CidadeTO getDestino() {
        return destino;
    }

    public void setDestino(CidadeTO destino) {
        this.destino = destino;
    }

    public Calendar getHoraOrigem() {
        return horaOrigem;
    }

    public void setHoraOrigem(Calendar horaOrigem) {
        this.horaOrigem = horaOrigem;
    }

    public Calendar getHoraDestino() {
        return horaDestino;
    }

    public void setHoraDestino(Calendar horaDestino) {
        this.horaDestino = horaDestino;
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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RotaTO)) {
            return false;
        }
        RotaTO other = (RotaTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rota[ id=" + id + " ]";
    }
    
}
