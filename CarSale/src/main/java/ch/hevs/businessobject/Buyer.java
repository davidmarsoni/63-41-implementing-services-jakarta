package ch.hevs.businessobject;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "Buyer")
public class Buyer extends Account {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@OneToMany(mappedBy = "buyer")
	private List<Sale> sales;

	public Buyer() {
	}

	public Buyer(String firstname, String lastname, String address, String phone, String email, Date birthdate) {
		super(firstname, lastname, address, phone, email, birthdate);
	}

	/**
	 * Get the id of the buyer.
	 * @return Long return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Get the list of sales of the buyer.
	 * @return List<Sale> return the sales
	 */
	public List<Sale> getSales() {
		return sales;
	}

}
