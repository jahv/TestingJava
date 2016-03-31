package jahv.java.testing.book.tdd.mockito.ch6.salary;

/**
 * Salary service class
 * 
 * @author jose.hernandez
 *
 */
public class SalaryService {

	private RepoService repoService;

	/**
	 * Constructor
	 * @param repoService
	 */
	public SalaryService(final RepoService repoService) {
		this.repoService = repoService;
	}

	/**
	 * Return salary minus taxes
	 * 
	 * @param id
	 * @return
	 */
	public Double getSalary(final String id) {
		final Double salary = repoService.getSalary(id);
		return salary * .15;
	}

	/**
	 * Updates salary
	 * 
	 * @param id
	 */
	public void updateSalary(final String id) {
		final Double salary = this.getSalary(id);
		repoService.updateData(id, salary);
	}
}
