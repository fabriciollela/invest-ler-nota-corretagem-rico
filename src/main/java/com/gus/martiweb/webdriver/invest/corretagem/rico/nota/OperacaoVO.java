package com.gus.martiweb.webdriver.invest.corretagem.rico.nota;

public class OperacaoVO {

	private String q;
	private String Negociacao;
	private String Cv;
	private String tipoMercado;
	private String Prazo;
	private String especificacaoDoTitulo;
	private String obs;
	private String quant;
	private String precoAjuste;
	private String valorAjuste;
	private String Dc;
	
	
	/**
	 * @return the negociacao
	 */
	public String getNegociacao() {
		return Negociacao;
	}
	/**
	 * @param negociacao the negociacao to set
	 */
	public void setNegociacao(String negociacao) {
		Negociacao = negociacao;
	}
	/**
	 * @return the cv
	 */
	public String getCv() {
		return Cv;
	}
	/**
	 * @param cv the cv to set
	 */
	public void setCv(String cv) {
		Cv = cv;
	}
	/**
	 * @return the tipoMercado
	 */
	public String getTipoMercado() {
		return tipoMercado;
	}
	/**
	 * @param tipoMercado the tipoMercado to set
	 */
	public void setTipoMercado(String tipoMercado) {
		this.tipoMercado = tipoMercado;
	}
	/**
	 * @return the prazo
	 */
	public String getPrazo() {
		return Prazo;
	}
	/**
	 * @param prazo the prazo to set
	 */
	public void setPrazo(String prazo) {
		Prazo = prazo;
	}
	/**
	 * @return the especificacaoDoTitulo
	 */
	public String getEspecificacaoDoTitulo() {
		return especificacaoDoTitulo;
	}
	/**
	 * @param especificacaoDoTitulo the especificacaoDoTitulo to set
	 */
	public void setEspecificacaoDoTitulo(String especificacaoDoTitulo) {
		this.especificacaoDoTitulo = especificacaoDoTitulo;
	}
	/**
	 * @return the obs
	 */
	public String getObs() {
		return obs;
	}
	/**
	 * @param obs the obs to set
	 */
	public void setObs(String obs) {
		this.obs = obs;
	}
	/**
	 * @return the quant
	 */
	public String getQuant() {
		return quant;
	}
	/**
	 * @param quant the quant to set
	 */
	public void setQuant(String quant) {
		this.quant = quant;
	}
	/**
	 * @return the precoAjuste
	 */
	public String getPrecoAjuste() {
		return precoAjuste;
	}
	/**
	 * @param precoAjuste the precoAjuste to set
	 */
	public void setPrecoAjuste(String precoAjuste) {
		this.precoAjuste = precoAjuste;
	}
	/**
	 * @return the valorAjuste
	 */
	public String getValorAjuste() {
		return valorAjuste;
	}
	/**
	 * @param valorAjuste the valorAjuste to set
	 */
	public void setValorAjuste(String valorAjuste) {
		this.valorAjuste = valorAjuste;
	}
	/**
	 * @return the dc
	 */
	public String getDc() {
		return Dc;
	}
	/**
	 * @param dc the dc to set
	 */
	public void setDc(String dc) {
		Dc = dc;
	}
	/**
	 * @return the q
	 */
	public String getQ() {
		return q;
	}
	/**
	 * @param q the q to set
	 */
	public void setQ(String q) {
		this.q = q;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OperacaoVO [Cv=" + Cv + ", especificacaoDoTitulo=" + especificacaoDoTitulo + ", quant=" + quant
				+ ", precoAjuste=" + precoAjuste + ", valorAjuste=" + valorAjuste + "]";
	}
	
}
