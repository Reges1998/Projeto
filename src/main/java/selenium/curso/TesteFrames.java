package selenium.curso;

import static selenium.curso.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TesteFrames {

	private DSL dsl;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Leticia\\Desktop\\chrome\\chromedriver.exe");
		getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		dsl = new DSL();
	}

//	@After
//	public void finaliza() throws InterruptedException {
//		Thread.sleep(1000);
//		driver.quit();
//}
	@Test
	public void deveinteragirComFrames() {
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);

		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", msg);
	}

	@Test
	public void deveInteragirComjanelas() {
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
//		driver.close();
		dsl.trocarJanela("");
		dsl.escrever(By.tagName("textarea"), "e agora?");

	}

	@Test
	public void deveInteragirComjanelaSemTitulo() {

		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
}
