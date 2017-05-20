/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.object;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author oliveira
 */
public class SimuladorObject implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer codigo;
    private Date dataHora;
    private Float lat;
    private Float lon;
    private Integer sendRequest;

    public SimuladorObject() {
    }

    
    public SimuladorObject(Integer codigo, Date dataHora, Float lat, Float lon, Integer sendRequest) {
        this.codigo = codigo;
        this.dataHora = dataHora;
        this.lat = lat;
        this.lon = lon;
        this.sendRequest = sendRequest;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Integer getSendRequest() {
        return sendRequest;
    }

    public void setSendRequest(Integer sendRequest) {
        this.sendRequest = sendRequest;
    }
    
    
}
