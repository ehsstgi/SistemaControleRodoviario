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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eduardo
 */

public class PassagemTO {
   
   
    private Long id;
  
    private LinhaTO linha;
  
    private RotaTO rota;
  
    private OnibusTO onibus;
   
    private AssentoTO assento;
  
    private Calendar horaSaida;
  
    private Calendar horaChegada;
 
    private Calendar dia;
    
    private UsuarioTO usuario;
  
    private FuncionarioTO funcionario;
    private double valor;
    private String nomeUsuario;
    private String RgUsuario;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getRgUsuario() {
        return RgUsuario;
    }

    public void setRgUsuario(String RgUsuario) {
        this.RgUsuario = RgUsuario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LinhaTO getLinha() {
        return linha;
    }

    public void setLinha(LinhaTO linha) {
        this.linha = linha;
    }

    public RotaTO getRota() {
        return rota;
    }

    public void setRota(RotaTO rota) {
        this.rota = rota;
    }

    public OnibusTO getOnibus() {
        return onibus;
    }

    public void setOnibus(OnibusTO onibus) {
        this.onibus = onibus;
    }

    public AssentoTO getAssento() {
        return assento;
    }

    public void setAssento(AssentoTO assento) {
        this.assento = assento;
    }

    public Calendar getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Calendar horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Calendar getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(Calendar horaChegada) {
        this.horaChegada = horaChegada;
    }

    public Calendar getDia() {
        return dia;
    }

    public void setDia(Calendar dia) {
        this.dia = dia;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

    public FuncionarioTO getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioTO funcionario) {
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


    
}
