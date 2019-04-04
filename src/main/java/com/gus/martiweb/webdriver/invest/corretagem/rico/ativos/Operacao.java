package com.gus.martiweb.webdriver.invest.corretagem.rico.ativos;

public class Operacao {

	private String data;

	private String ticker;
	
	private String op;
	
	private String qtd;
	
	private String preco;
	
	private String custo;

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @param ticker
	 *            the ticker to set
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 * @return the op
	 */
	public String getOp() {
		return op;
	}

	/**
	 * @param op
	 *            the op to set
	 */
	public void setOp(String op) {
		this.op = op;
	}

	/**
	 * @return the qtd
	 */
	public String getQtd() {
		return qtd;
	}

	/**
	 * @param qtd
	 *            the qtd to set
	 */
	public void setQtd(String qtd) {
		this.qtd = qtd;
	}

	/**
	 * @return the preco
	 */
	public String getPreco() {
		return preco;
	}

	/**
	 * @param preco
	 *            the preco to set
	 */
	public void setPreco(String preco) {
		this.preco = preco;
	}

	/**
	 * @return the custo
	 */
	public String getCusto() {
		return custo;
	}

	/**
	 * @param custo
	 *            the custo to set
	 */
	public void setCusto(String custo) {
		this.custo = custo;
	}
	
	
}
