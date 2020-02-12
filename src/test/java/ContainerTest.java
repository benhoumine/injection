
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.benhoumine.inject.main.model.*;
import com.benhoumine.inject.container.ContainerID;
import com.benhoumine.inject.exceptions.AucuneImplementationException;

public class ContainerTest {

	private static ContainerID container;

	@BeforeAll
	static void initAll() {
		container = new ContainerID();
	}

	@Test
	@DisplayName("Test Injection Bank")
	void TestBank() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException,
			AucuneImplementationException {
		BudgetModule module = new BudgetModule();
		container.injectClass(module);
		assertTrue(module.getBank() instanceof BMCI);

	}

	@Test
	@DisplayName("Test Injection Employe Bank")
	void TestEmploye() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException,
			AucuneImplementationException {
		BudgetModule module = new BudgetModule();
		container.injectClass(module);
		assertTrue(module.getInterneBMCI() instanceof EmployeBMCIBANK);
	}

	@Test
	@DisplayName("Test Injection Bank new Instance ")
	void TestNewInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		BudgetModule module = new BudgetModule();
		assertFalse(module.getBank() instanceof CreditAgricool);
		module.setBank(container.newInstance(CreditAgricool.class));
		assertTrue(module.getBank() instanceof CreditAgricool);
	}

	
	@Test
	@DisplayName("Test Employe new Instance ")
	void TestNewInstanceEmploye() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		BudgetModule module = new BudgetModule();
		assertFalse(module.getBank() instanceof CreditAgricool);
		module.setInterneBMCI(container.newInstance(EmployeBMCIBANK.class));
		assertTrue(module.getInterneBMCI() instanceof EmployeBMCIBANK);
	}

	
	@Test
	@DisplayName("Test Film throw Aucune Implementation Exception")
	void TestFilm() {
		ListFilm listFilm = new ListFilm();
		Assertions.assertThrows(AucuneImplementationException.class, () -> {
			container.injectClass(listFilm);
		});
	}

	@Test
	@DisplayName("Test OnlyOne annotation - Singleton")
	void TestOnlyOneSingleton() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			IOException, AucuneImplementationException {
		BudgetModule module = new BudgetModule();
		container.injectClass(module);
		assertTrue(module.getBank() instanceof BMCI);
		assertTrue(module.getInterneBMCI() instanceof EmployeBMCIBANK);
		assertEquals(((BMCI) module.getBank()).getCompter() , 1);
		assertEquals(((EmployeBMCIBANK) module.getInterneBMCI()).getCompter() , 4);
		TestOnlyOne singleton = new TestOnlyOne();
		container.injectClass(singleton);
		assertTrue(singleton.getBank() instanceof BMCI);
		assertEquals(((BMCI) singleton.getBank()).getCompter(), 1);
		assertEquals(((EmployeBMCIBANK) module.getInterneBMCI()).getCompter() , 5);

	}

}
