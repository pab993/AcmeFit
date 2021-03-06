
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	//Busca un actor por su userAccount id
	@Query("select c from Customer c where c.userAccount.id = ?1")
	Customer findByUserAccountId(int userAccountId);

	//Esta query se usa porque por alguna raz�n falla en el covertidor el findOne normal
	//	@Query("select c from Customer c where c.id=?1")
	//	Customer findOne2(int customerId);

	@Query("select c from Customer c join c.invoices i order by sum(i.totalPrice)")
	Collection<Customer> rankingCustomersByInvoices();

	//The minimum, the average, and the maximum number of diets per customer
	@Query("select min(c.requestDiets.size) from Customer c")
	Double minDietsByCustomer();

	@Query("select avg(c.requestDiets.size) from Customer c")
	Double avgDietsByCustomer();

	@Query("select max(c.requestDiets.size) from Customer c")
	Double maxDietsByCustomer();

	//The customers that have spent at least 70% the maximum amount of money that other customers has spent
	@Query("select c from Customer c join c.invoices i where (i.totalPrice*0.7 >=(select avg(i1.totalPrice) from Invoice i1))")
	Collection<Customer> findCustomerSpentMore70();

	//The customer/s who has/have sign up more activities
	@Query("select c from Customer c where c.registersFor.size >= all(select c1.registersFor.size from Customer c1)")
	Collection<Customer> findCustomerMoreActivities();

	//The customer/s who has/have sign up less activities
	@Query("select c from Customer c where c.registersFor.size <= all(select c1.registersFor.size from Customer c1)")
	Collection<Customer> findCustomerLessActivities();

	@Query("select b.customer from Booking b where b.status = 'CANCELED' group by b.customer having count(b) >= all(select count(b1) from Booking b1 where b1.status = 'CANCELED' group by b1.customer)")
	Collection<Customer> customerWithMoreCanceledBookings();
}
