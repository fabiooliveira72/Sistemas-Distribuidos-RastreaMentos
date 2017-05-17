/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author eduardo
 */
public class Posicao implements Serializable{
    
    private Integer seq;
    private Date datahora;
    private Float latitude;
    private Float longitude;
    private Veiculo veiculo;
   
    public Posicao() {
    }

    public Posicao(Integer seq, Date datahora, Float latitude, Float longitude, Veiculo veiculo) {
        this.seq = seq;
        this.datahora = datahora;
        this.latitude = latitude;
        this.longitude = longitude;
        this.veiculo = veiculo;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }


    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return "Posicao{" + "seq=" + seq + ", datahora=" + datahora + ", latitude=" + latitude + ", longitude=" + longitude + ", veiculo=" + veiculo + '}';
    }
    
    
}
