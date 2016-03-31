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
public class SalaryService_MockitoConsecutiveCalls_Test {

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
	 * Testing consecutive calls
	 */
	@Test(expected = NumberFormatException.class)
	public void test_consecutive_calls() {
		final Double[] responses = new Double[] { 0.0, 1.0, 2.0, 3.0 };
		Mockito.when(repoService.getSalary(Mockito.anyString())).thenReturn(responses[0]).thenReturn(responses[1])
				.thenReturn(responses[2]).thenReturn(responses[3]).thenThrow(new NumberFormatException());

		Assertions.assertThat(salaryService.getSalary(Mockito.anyString())).isEqualTo(responses[0] * .15);
		Assertions.assertThat(salaryService.getSalary(Mockito.anyString())).isEqualTo(responses[1] * .15);
		Assertions.assertThat(salaryService.getSalary(Mockito.anyString())).isEqualTo(responses[2] * .15);
		Assertions.assertThat(salaryService.getSalary(Mockito.anyString())).isEqualTo(responses[3] * .15);

		// This one is to throw the exception
		salaryService.getSalary(Mockito.anyString());
	}

}
