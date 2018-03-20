package br.com.livro.domain;

/*import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;*/
import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement         //  <---CONVERTER CLASSES     

public class Carro implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long cod_veiculo;
	private String fabricante;                                     // ATRIBUTOS
	private String modelo;
	private String especificacoes;
	private String anoFabricacao;
	private String anoModelo;
	private String tipoCombustivel;
	private String valor;
	private String placa;
	private String cor;
	private Date data;

	public java.sql.Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getCod_veiculo() {
		return cod_veiculo;
	}



	public void setCod_veiculo(Long cod_veiculo) {
		this.cod_veiculo = cod_veiculo;
	}



	public String getFabricante() {
		return fabricante;
	}



	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}



	public String getModelo() {
		return modelo;
	}



	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public String getEspecificacoes() {
		return especificacoes;
	}



	public void setEspecificacoes(String especificacoes) {
		this.especificacoes = especificacoes;
	}



	public String getAnoFabricacao() {
		return anoFabricacao;
	}



	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}



	public String getAnoModelo() {
		return anoModelo;
	}



	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}



	public String getTipoCombustivel() {
		return tipoCombustivel;
	}



	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}



	public String getValor() {
		return valor;
	}



	public void setValor(String valor) {
		this.valor = valor;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override                     // Ao utilizado para vc definir a string de saida quando forem imprimir seu objeto ou forem converte-lo.
	public String toString() {                                 // TO STRING 
		return "Carro [cod_veiculo=" + cod_veiculo + "," + " fabricante=" + fabricante + "," + " modelo=" + modelo + "," + " especificacoes=" + especificacoes + ","
				+ " anoFabricacao=" + anoFabricacao + "," + " anoModelo=" + anoModelo + "," + " tipoCombustivel=" + tipoCombustivel + ", "
				+ "valor=" + valor+","+"placa="+placa+","+"cor=" +cor+","+"data="+data+"]";
	}
	
	
}