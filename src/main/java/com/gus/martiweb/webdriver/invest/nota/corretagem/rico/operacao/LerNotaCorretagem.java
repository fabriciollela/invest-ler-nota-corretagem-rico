package com.gus.martiweb.webdriver.invest.nota.corretagem.rico.operacao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gus.martiweb.webdriver.invest.corretagem.rico.ativos.Operacao;
import com.gus.martiweb.webdriver.invest.corretagem.rico.nota.NotaVO;
import com.gus.martiweb.webdriver.invest.corretagem.rico.nota.OperacaoVO;

public class LerNotaCorretagem {
	/*
	 * Q Negociação C/V Tipo Mercado Prazo Especificação do Título Obs Quant. Preço/Ajuste
	 * Valor/Ajuste D/C 1-BOVESPA C VISTA AMBEV S/A ON H 300 16,78 5.034,00 D 1-BOVESPA C VISTA
	 * AMBEV S/A ON H 100 16,78 1.678,00 D 1-BOVESPA V VISTA HYPERMARCAS ON NM H 100 25,73 2.573,00
	 * C 1-BOVESPA V VISTA HYPERMARCAS ON NM H 100 25,73 2.573,00 C 1-BOVESPA V VISTA HYPERMARCAS ON
	 * NM H 100 25,73 2.573,00 C
	 */

	private static final Integer QTD_COLUNAS = 11;

	private static final String GOOGLE = "https://www.google.com/search?q=";

	private LerNotaCorretagem() {
	}
	
	public static List<Operacao> montaOperacoes(List<NotaVO> notas){
		List<Operacao> operacoes = new ArrayList<>();
		
		for(NotaVO nota : notas){
			Date data = new Date(nota.getDataCompra().getTime());
			for(OperacaoVO operacaoVo : nota.getAtivos()){
				Operacao operacao = new Operacao();
				operacao.setData(new SimpleDateFormat("yyyy-MM-dd").format(data));
				operacao.setTicker(operacaoVo.getEspecificacaoDoTitulo());
				operacao.setOp(operacaoVo.getCv());
				operacao.setQtd(operacaoVo.getQuant());
				operacao.setPreco(operacaoVo.getPrecoAjuste());
				operacao.setCusto("0");
				operacoes.add(operacao);
			}
		}
		
		return operacoes;
	}

	public static NotaVO lerNotaGeral(WebDriver driver) {
		NotaVO notaVO = new NotaVO();

		StringBuilder data = new StringBuilder(driver.findElement(By.xpath("//*[@id=\"pnlNota\"]/table[1]/tbody/tr[1]/td[2]")).getText());
		String labelPregao = driver.findElement(By.xpath("//*[@id=\"pnlNota\"]/table[1]/tbody/tr[1]/td[2]/span[2]")).getText();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			notaVO.setDataCompra(sdf.parse(data.subSequence(data.lastIndexOf(labelPregao)+labelPregao.length(), data.length()).toString().trim()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		notaVO.setAtivos(lerAtivos(driver.findElement(By.xpath("//*[@id=\"pnlNota\"]/table[2]/tbody"))));
		return notaVO;
	}

	public static List<OperacaoVO> lerAtivos(WebElement tabelaAtivos) {
		List<OperacaoVO> ativoVOs = new ArrayList<OperacaoVO>();
		List<WebElement> ativos = tabelaAtivos.findElements(By.xpath(".//*"));
		int count = 0;
		Field[] fields = OperacaoVO.class.getDeclaredFields();
		Integer contadorColunas = 0;
		OperacaoVO operacaoVO = null;
		for (WebElement ativo : ativos) {
			if (ativo.getTagName().equals("td")) {
				if (count <= QTD_COLUNAS) {
					count++;
				} else {
					if (contadorColunas.equals(0)) {
						operacaoVO = new OperacaoVO();
					}
					Field field = fields[contadorColunas++];
					String campo = field.getName();
					String nomeMetodo = "set" + campo.substring(0, 1).toUpperCase() + campo.substring(1);
					Method method = null;
					try {
						method = operacaoVO.getClass().getMethod(nomeMetodo, field.getType());
						method.invoke(operacaoVO, ativo.getText());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
					if (contadorColunas.equals(QTD_COLUNAS)) {
						contadorColunas = 0;
						ativoVOs.add(operacaoVO);
						// System.out.println(operacaoVO.toString());
//						try {
//							String url = GOOGLE + "\"infoMoney\"+" + operacaoVO.getEspecificacaoDoTitulo().replaceAll(" ", "+") + "&num=20";
////
////							Document doc = Jsoup.connect(url)
////							        .userAgent(
////							                "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
////							        .timeout(5000).get();
////							String result = Xsoup
////							        .compile("//*[@id=\"rso\"]/div/div/div[1]/div/div/table/tbody/tr[1]/td/h3/a")
////							        .evaluate(doc).get();
////							System.out.println(result);
//						} catch (IOException e) {
//							e.printStackTrace();
//						}

					}
				}
			}
		}
		// //*[@id=\"rso\"]/div/div/div[1]/div/div/table/tbody/tr[1]/td/h3/a

		return ativoVOs;
	}
}
