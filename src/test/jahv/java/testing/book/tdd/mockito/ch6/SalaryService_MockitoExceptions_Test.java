package jahv.java.testing.book.tdd.mockito.ch6;

import jahv.java.testing.book.tdd.mockito.ch6.salary.RepoService;
import jahv.java.testing.book.tdd.mockito.ch6.salary.SalaryService;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Testing class for {@link SalaryService}
 * 
 * @author jose.hernandez
 *
 */
public class SalaryService_MockitoExceptions_Test {

	private SalaryService salaryService;

	@Mock
	private RepoService repoService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		salaryService = new SalaryService(repoService);
	}

	@Test
	public void test_AnnotationsInit() {
		Assertions.assertThat(repoService).isNotNull();
	}

	/**
	 * Testing the repo fails
	 */
	@Test(expected = RuntimeException.class)
	public void test_repoService_fails() {
		Mockito.when(repoService.getSalary(Mockito.anyString())).thenThrow(new RuntimeException());

		salaryService.getSalary("id");
	}

	/**
	 * Testing void method failing
	 */
	@Test(expected = RuntimeException.class)
	public void test_voidMethod_fails() {
		Mockito.when(repoService.getSalary(Mockito.anyString())).thenReturn(100.0);
		Mockito.doThrow(new RuntimeException()).when(repoService).updateData(Mockito.anyString(), Mockito.anyDouble());

		salaryService.updateSalary("id");
	}

	/**
	 * Testing consecutive calls
	 */
	@Test(expected = NumberFormatException.class)
	public void test_consecutive_calls() {
		final Double[] responses = new Double[] { 0.0, 1.0, 2.0, 3.0 };
		Mockito.when(repoService.getSalary(Mockito.anyString())).thenReturn(responses[0]).thenReturn(responses[1])
				.thenReturn(responses[2]).thenReturn(responses[3]).thenThrow(new NumberFormatException());

		Assertions.assertThat(repoService.getSalary(Mockito.anyString())).isEqualTo(responses[0]);
		Assertions.assertThat(repoService.getSalary(Mockito.anyString())).isEqualTo(responses[1]);
		Assertions.assertThat(repoService.getSalary(Mockito.anyString())).isEqualTo(responses[2]);
		Assertions.assertThat(repoService.getSalary(Mockito.anyString())).isEqualTo(responses[3]);

		// This one is to throw the exception
		repoService.getSalary(Mockito.anyString());
	}

}
