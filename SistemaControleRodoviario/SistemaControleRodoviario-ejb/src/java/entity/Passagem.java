/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eduardo
 */
@Entity
@NamedQueries({
@NamedQuery(   name="passagemPorUsuario",
        query="SELECT OBJECT(p) FROM Passagem p "
        + "WHERE p.usuario = :usuario"),
@NamedQuery(   name="passagemPorUsuarioCount",
        query="SELECT Count(p) FROM Passagem p "
        + "WHERE p.usuario = :usuario"),
@NamedQuery(   name="verificaPassagem",
        query="SELECT OBJECT(p) FROM Passagem p "
        + "WHERE p.assento = :assento "
        + "AND p.dia = :dia "
        + "AND p.rota = :rota AND p.onibus = :onibus"),
@NamedQuery(   name="passagemPorFuncionario",
        query="SELECT OBJECT(p) FROM Passagem p "
        + "WHERE p.funcionario = :funcionario"),
@NamedQuery(   name="passagemWeb",
        query="SELECT OBJECT(p) FROM Passagem p "
        + "WHERE p.usuario IS NOT Null"),
        })
public class Passagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Linha linha;
    @ManyToOne
    private Rota rota;
    @ManyToOne
    private Onibus onibus;
    @ManyToOne
    private Assento assento;
    @Temporal(TemporalType.DATE)
    private Date dia;
    @ManyToOne
    private Horario horarioSaida;
    @ManyToOne
    private Horario horarioChegada;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Funcionario funcionario;
    private double valor;
    private String nomeUsuario;
    private String rgUsuario;

    public Horario getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(Horario horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public Horario getHorarioChegada() {
        return horarioChegada;
    }

    public void setHorarioChegada(Horario horarioChegada) {
        this.horarioChegada = horarioChegada;
    }


    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getRgUsuario() {
        return rgUsuario;
    }

    public void setRgUsuario(String rgUsuario) {
        this.rgUsuario = rgUsuario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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
        if (!(object instanceof Passagem)) {
            return false;
        }
        Passagem other = (Passagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Passagem[ id=" + id + " ]";
    }
    
}
