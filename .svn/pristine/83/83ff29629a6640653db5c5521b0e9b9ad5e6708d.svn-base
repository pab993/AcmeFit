
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	@Query("select i from Invoice i where i.customer.userAccount.id = ?1")
	Invoice findByUserAccount(int userAccountId);

	@Query("select i from Invoice i where i.customer.id = ?1")
	Collection<Invoice> findByCustomerId(int customerId);
}
