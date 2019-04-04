package com.gus.martiweb.webdriver.invest.nota.corretagem.rico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gus.martiweb.webdriver.invest.corretagem.rico.ativos.Carteira;
import com.gus.martiweb.webdriver.invest.corretagem.rico.nota.NotaVO;
import com.gus.martiweb.webdriver.invest.nota.corretagem.rico.operacao.LerNotaCorretagem;

/**
 * Hello world!
 *
 */
public class App {
	
	private static List<NotaVO> notas = new ArrayList<>();
	
	
	public static void main(String[] args) throws InterruptedException {
		// declaration and instantiation of objects/variables
		WebDriver driver;
		// System.setProperty("webdriver.firefox.marionette",
		// "C:\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Gustavo Martinelli\\Documents\\chromedriver.exe");
		driver = new ChromeDriver();
		String baseUrl = "http://www.rico.com.vc";
		String actualTitle = "Rico";

		String[] senha = { "D", "D", "D", "D", "D", "D" };

		// launch Fire fox and direct it to the Base URL
		driver.get(baseUrl);

		// get the actual value of the title
		actualTitle = driver.getTitle();
		driver.findElement(By.xpath("//*[@id=\"PublicWrapper\"]/header/div/div[2]/div[2]/login-link/div/div[2]/div"))
				.click();
		driver.findElement(By.xpath("//*[@id=\"login-component\"]/div/div[2]/div/div[1]/div/form/div[1]/input"))
				.sendKeys("gustavogmartinelli");
		;

		do {
			driver.findElement(By.xpath("//*[@id=\"login-component\"]/div/div[2]/div/div[1]/div/form/button")).click();
			driver.manage().timeouts().implicitlyWait(1l, TimeUnit.SECONDS);
		} while (!driver.findElements(By.className("error-feedback")).isEmpty() && driver
				.findElement(By.className("error-feedback")).getText().equalsIgnoreCase("Usuário não encontrado."));
		driver.manage().timeouts().implicitlyWait(5l, TimeUnit.SECONDS);
		List<WebElement> findElement = driver.findElements(By.className("btn-blue-xlight"));
		for (String num : senha) {
			for (WebElement element : findElement) {
				if (element.getText().contains(num)) {
					element.click();
					break;
				}
			}
		}
		driver.findElement(By.xpath("//*[@id=\"login-component\"]/div/div[2]/div/div[1]/div/form/button")).click();
		driver.manage().timeouts().implicitlyWait(20l, TimeUnit.SECONDS);
		List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[7]/div/div[5]/a[1]"));
		for (WebElement element : elements) {
			if (element.getTagName().equals("a") && element.getText().equalsIgnoreCase("Sair")) {
				element.click();
				break;
			}
		}

		// Login
		// Expande a Aba Ações
		driver.manage().timeouts().implicitlyWait(50l, TimeUnit.SECONDS);
		WebElement elemExpandable = driver
				.findElement(By.xpath("/html/body/section/div/div[1]/nav/ul[1]/li[1]/a/span"));

		Actions actions = new Actions(driver);

		actions.moveToElement(elemExpandable).click().perform();
		// driver.findElement(By.xpath("/html/body/section/div/div[1]/nav/ul[1]/li[1]/a/span")).click();
		driver.manage().timeouts().implicitlyWait(5l, TimeUnit.SECONDS);

		// Seleciona Notas de corretagem
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/section/div/div[1]/nav/ul[1]/li[1]/ul/li[6]/a")));
		driver.findElement(By.xpath("/html/body/section/div/div[1]/nav/ul[1]/li[1]/ul/li[6]/a")).click();

		boolean hasProximaPagina = true;
		int contadorPagina = 1;
		driver.switchTo().frame(driver.findElement(By.id("iframe-brokerage-notes")));
		while (hasProximaPagina) {

			 carregaNotas(driver);

			List<WebElement> paginacao = driver
					.findElement(By.xpath("//*[@id=\"ctl00_MainContent_dtgBrokerageNote\"]/tbody/tr[12]/td"))
					.findElements(By.xpath(".//*"));
			contadorPagina++;
			// Por enquanto será assim

			for (WebElement element : paginacao) {
				// System.out.println(element.getText());
				Integer pagina = null;
				try {
					pagina = Integer.valueOf(element.getText());
					if (pagina.equals(contadorPagina) && element.getTagName().equals("a")) {
						element.click();
						break;
					}
				} catch (NumberFormatException e) {
					// Se houver erro na conversão, retorna o valor padrão
					if (element.getText().equals("...")) {
						element.click();
						hasProximaPagina = Boolean.FALSE;
					}
				}

			}
		}
		LerNotaCorretagem.montaOperacoes(notas);
	}

	private static void carregaNotas(WebDriver driver) {
		boolean futuro = false;
		boolean normal = false;
		String parentWindowHandler = driver.getWindowHandle();
		List<WebElement> tabela = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_dtgBrokerageNote\"]/tbody"))
				.findElements(By.xpath(".//*"));
		for (WebElement element : tabela) {
			if (element.getTagName().equals("td") && element.getText().contains("NOR")) {
				normal = true;
				continue;
			} else if (element.getTagName().equals("td") && element.getText().contains("FUT")) {
				futuro = true;
				continue;
			}
			if (element.getTagName().equals("td") && element.getText().contains("VISUALIZAR")) {
				// Store your parent window
				String subWindowHandler = null;
				element.click();

				Set<String> handles = driver.getWindowHandles(); // get all
																	// window
																	// handles
				Iterator<String> iterator = handles.iterator();
				while (iterator.hasNext()) {
					subWindowHandler = iterator.next();
				}
				driver.switchTo().window(subWindowHandler); // switch to popup
															// window

				if (futuro) {
					carregaNotasFut(driver);
				} else if (normal) {
					carregaNotasNor(driver);
				}
				futuro = false;
				normal = false;
				driver.switchTo().window(parentWindowHandler);
				driver.switchTo().frame(driver.findElement(By.id("iframe-brokerage-notes")));
				continue;
			}
		}
		
	}

	private String normal = "XBSP/Movement - NOR";
	private String futuro = "XBSP/Movement - FUT";

	private static void carregaNotasFut(WebDriver driver) {
		// LerNotaCorretagem.lerNotaGeral(driver);
		return;
	}

	private static void carregaNotasNor(WebDriver driver) {
		notas.add(LerNotaCorretagem.lerNotaGeral(driver));
		return;
	}

}
