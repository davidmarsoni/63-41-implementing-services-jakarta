package ch.hevs.businessobject;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "Buyer")
public class Buyer extends Account {
	@Id
	@GeneratedValue
	private Long id;

	private String creditCard;

	@OneToMany(mappedBy = "buyer")
	private List<Sale> sales;

	public Buyer() {
	}

	public Buyer(String firstname, String lastname, String address, String phone, String email, Date birthdate, String creditCard) {
		super(firstname, lastname, address, phone, email, birthdate);
		this.creditCard = creditCard;
	}

	/**
	 * Get the id of the buyer.
	 * @return Long return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Get the credit card of the buyer.
	 * @return String return the credit card
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * Set the credit card of the buyer.
	 * @param creditCard the credit card to set
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * Get the list of sales of the buyer.
	 * @return List<Sale> return the sales
	 */
	public List<Sale> getSales() {
		return sales;
	}

}
