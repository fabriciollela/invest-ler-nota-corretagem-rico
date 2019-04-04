package com.gus.martiweb.webdriver.invest.corretagem.rico.nota;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotaVO {
//	Caminho do resumo da nota
	// //*[@id="pnlNota"]/table[3]/tbody/tr[1]/td[2]/table/tbody

	private List<OperacaoVO> ativos = new ArrayList<OperacaoVO>();
	
	private Date dataCompra;
	
	private Double txLiquidacao;
	
	private Double corrtagem;
	
	private Double emolumentos;

	/**
	 * @return the ativos
	 */
	public List<OperacaoVO> getAtivos() {
		return ativos;
	}

	/**
	 * @param ativos the ativos to set
	 */
	public void setAtivos(List<OperacaoVO> ativos) {
		this.ativos = ativos;
	}

	/**
	 * @return the dataCompra
	 */
	public Date getDataCompra() {
		return dataCompra;
	}

	/**
	 * @param dataCompra the dataCompra to set
	 */
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	/**
	 * @return the txLiquidacao
	 */
	public Double getTxLiquidacao() {
		return txLiquidacao;
	}

	/**
	 * @param txLiquidacao the txLiquidacao to set
	 */
	public void setTxLiquidacao(Double txLiquidacao) {
		this.txLiquidacao = txLiquidacao;
	}

	/**
	 * @return the corrtagem
	 */
	public Double getCorrtagem() {
		return corrtagem;
	}

	/**
	 * @param corrtagem the corrtagem to set
	 */
	public void setCorrtagem(Double corrtagem) {
		this.corrtagem = corrtagem;
	}

	/**
	 * @return the emolumentos
	 */
	public Double getEmolumentos() {
		return emolumentos;
	}

	/**
	 * @param emolumentos the emolumentos to set
	 */
	public void setEmolumentos(Double emolumentos) {
		this.emolumentos = emolumentos;
	}
	
	

}
