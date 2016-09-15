/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

/**
 *
 * @author Luis08
 */

public class ArchivoJson {

	private int id, puerto;
	private String  ip, tipoDato, valorDato, tipoNodo;
	//private BigDecimal salary;
	//private List<String> skills;

    public int getId() {
        return id;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String pIp) {
        this.ip = pIp;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int pPuerto) {
        this.puerto = pPuerto;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String pTipoDato) {
        this.tipoDato = pTipoDato;
    }

    public String valorDato() {
        return valorDato;
    }

    public void setValorDato(String pValorDato) {
        this.valorDato = pValorDato;
    }
    
    public String getTipoNodo() {
        return tipoNodo;
    }

    public void setTipoNodo(String pTipoNodo) {
        this.tipoNodo = pTipoNodo;
    }
}