

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
	void TestBank() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, AucuneImplementationException {
			BudgetModule module = new BudgetModule();
			container.injectClass(module);
			assertTrue(module.getBank() instanceof BMCI);
			module.setBank((Bank) container.newInstance(CreditAgricool.class));
			assertTrue(module.getBank() instanceof CreditAgricool);
	}
	
	@Test
	@DisplayName("Test Employe Bank")
	void TestEmploye() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, AucuneImplementationException {
			BudgetModule module = new BudgetModule();
			container.injectClass(module);
			assertTrue(module.getInterneBMCI() instanceof EmployeBMCIBANK);
	}

	@Test
	@DisplayName("Test Film throw Aucune Implementation Exception")
	void TestFilm(){
			ListFilm listFilm = new ListFilm("test");
			Assertions.assertThrows(AucuneImplementationException.class, ()->{
				container.injectClass(listFilm) ; 
			}) ; 
	}

	
}
