package selenium.curso;

import static selenium.curso.DriverFactory.getDriver;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteSincronismo {

	private DSL dsl;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Leticia\\Desktop\\chrome\\chromedriver.exe");
		getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		dsl = new DSL();

	}

//	@After
//	public void finaliza() {
//		driver.quit();
//	}

	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escrever("novoCampo", "Deu certo?");
	}

	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicarBotao("buttonDelay");
		dsl.escrever("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

	}

	@Test
	public void deveUtilizarEsperaExplicia() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Deu certo?");

	}

}
