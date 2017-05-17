package Entidades;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardo
 */
public class Veiculo implements Serializable{
    
    private Integer codigo;
    private String placa;
    private Integer tipo;
    private Integer capacidade;
    private String unpac;

    public Veiculo() {
    }

    public Veiculo(Integer codigo, String placa, Integer tipo, Integer capacidade, String unpac) {
        this.codigo = codigo;
        this.placa = placa;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.unpac = unpac;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getUnpac() {
        return unpac;
    }

    public void setUnpac(String unpac) {
        this.unpac = unpac;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "codigo=" + codigo + ", placa=" + placa + ", tipo=" + tipo + ", capacidade=" + capacidade + ", unpac=" + unpac + '}';
    }
    
}
