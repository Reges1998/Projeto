package selenium.curso;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegraCadastro.class
})
public class SuiteTeste {

}
