package selenium.curso;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePrime {
	private WebDriver driver;
	private DSL dsl;

@Before
public void inicializa(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Leticia\\Desktop\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=b7d52");
//		dsl = new DSL(driver);
		

//		@After
//		public void finaliza() throws InterruptedException {
//			Thread.sleep(1000);
//			driver.quit();
	//}
		
	}

	@Test
	public void deveInteragirComRadioPrime(){
		dsl.clicarRadio("//input[@id='j_idt287:line:0']/../..//span");
	}
}
