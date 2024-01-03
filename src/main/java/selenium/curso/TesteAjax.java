package selenium.curso;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax {
	private DSL dsl;

	@Before
	public void inicializa() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Leticia\\Desktop\\chrome\\chromedriver.exe");
//			driver = new ChromeDriver();
//			driver.manage().window().setSize(new Dimension(1200, 765));
			DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=8985a");
			dsl = new DSL();
			
		}
	
	@After
	
	public void finaliza() {
//		driver.quit();
		DriverFactory.killDriver();
	}
	

	@Test
	public void testeAjax() {
		dsl.escrever("j_idt286:name", "Teste");
		dsl.clicarBotao("j_idt286:j_idt290");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt286:display"), "Teste"));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt286:display"));
	}

	private WebDriver getDriver() {
		// TODO Auto-generated method stub
		return null;
	}
}
