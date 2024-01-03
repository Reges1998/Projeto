package selenium.curso;

import static selenium.curso.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TesteCampoTreinamento {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Leticia\\Desktop\\chrome\\chromedriver.exe");
		getDriver().get("file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
		// getDriver.get("file:\\" + System.getProperty("user.dir") +
		// "\\src\\main\\resources\\componentes.html");
		dsl = new DSL();

	}

//	@After
//	public void finaliza() {
//		driver.quit();
//	}

	@Test
	public void testeTexfield() {
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));

	}

	@Test
	public void deveInteragirComTextArea() {
		dsl.escrever("elementosForm:sugestoes", "teste\n\naasldjdlksznUltima linha");
		Assert.assertEquals("teste\n\naasldjdlksznUltima linha", dsl.obterValorCampo("elementosForm:sugestoes"));

	}

	@Test
	public void deveInteragirComRadioButton() {

		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}

	@Test
	public void deveInteragirComCheckbox() {
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
	}

	@Test
	public void deveInteragirComCombo() {

		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau incompleto");
		Assert.assertEquals("2o grau incompleto", dsl.obterValorCombo("elementosForm:escolaridade"));
	}

	@Test
	public void deveVerificarValoresCombo() {

		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}

//	@Test
//	public void deveVerificarValoresComboMultiplo(){
//		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
//		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
//		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
//	
//		
//		List<String> opcoesMarcadas = dsl.obterValorCombo("elementosForm:esportes");
//		Assert.assertEquals(3, opcoesMarcadas.size());
//		
//		dsl.deselecionarCombo("elementosForm:esportes","Corrida");
//		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
//		Assert.assertEquals(2, opcoesMarcadas.size());
//		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
//	}

	@Test
	@Ignore
	public void deveVerificarValoresCombotoes() {
		dsl.clicarBotao("buttonSimple");
		driver.findElement(By.id("buttonSimple")).click();
	}

	@Test
	public void deveInteragirComLinks() {
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));

	}

	@Test
	public void deveBuscarTextosNaPagina() {

//		System.out.println(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}

	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//		js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}

	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Botao", "elementosForm:tableUsuarios");
	}
}
