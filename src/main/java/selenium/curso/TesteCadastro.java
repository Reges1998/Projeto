package selenium.curso;

import static selenium.curso.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TesteCadastro {
	
//	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() throws InterruptedException {
//			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Leticia\\Desktop\\chrome\\chromedriver.exe");
//			driver = new ChromeDriver();
//			driver.manage().window().setSize(new Dimension(1200, 765));
			getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
//			dsl = new DSL(driver); 
//			page = new CampoTreinamentoPage();
//			Thread.sleep(3000);
		}

	@After
	public void finaliza() throws InterruptedException {		
	DriverFactory.killDriver();
}

	@Test
	public void deveInteragirComLinks(){
	
		page.setName("Leticia");	
		page.setSobrenome("Reges");	
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();

		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Leticia", page.obterNomeCadastro());
		Assert.assertEquals("Reges", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEspertesCadastro());
		
	}
	
	@Test
	public void deveValidarNomeObrigatorio(){
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());

	}
	@Test
	public void deveValidarSobrenomeObrigatorio(){
		page.setName("Nome qualquer");
		page.cadastrar();

		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	

	
	}
	@Test
	public void deveValidarSexoObrigatorio(){
		page.setName("Nome qualquer");
		page.setSobrenome(null);
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
		
	}
	@Test
	public void deveValidarComidaVegetariana(){
		
		page.setName("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
		
	}
	@Test
	public void deveValidarEsportistaIndeciso(){
		page.setName("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEsporte("Karate","O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
	
	}
}
