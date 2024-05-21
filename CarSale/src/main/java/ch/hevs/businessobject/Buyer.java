package ch.hevs.businessobject;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

/**
 * Represents a buyer.
 */
@Entity
@Table(name = "Buyer")
public class Buyer extends Account {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@OneToMany(mappedBy = "buyer") // no need to cascade here the sale is keep even if the buyer is deleted
	private List<Sale> sales;

	/**
	 * Constructs a new Buyer object.
	 */
	public Buyer() {
	}

	/**
	 * Constructs a new Buyer object with the specified details.
	 *
	 * @param username  the username of the buyer
	 * @param firstname the first name of the buyer
	 * @param lastname  the last name of the buyer
	 * @param address   the address of the buyer
	 * @param phone     the phone number of the buyer
	 * @param email     the email address of the buyer
	 * @param birthdate the birthdate of the buyer 
	 */
	public Buyer(String username, String firstname, String lastname, String address, String phone, String email, LocalDate birthdate) {
		super(username, firstname, lastname, address, phone, email, birthdate);
	}

	/**
	 * Get the id of the buyer.
	 * 
	 * @return Long the id of the buyer
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Get the list of sales of the buyer.
	 * 
	 * @return List<Sale> the list of sales of the buyer
	 */
	public List<Sale> getSales() {
		return sales;
	}

	/**
	 * Set the list of sales of the buyer.
	 * 
	 * @param sales the list of sales of the buyer
	 */
	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	/**
	 * Add a sale to the list of sales of the buyer.
	 * 
	 * @param sale the sale to add to the list of sales of the buyer
	 */
	public void addSale(Sale sale) {
		sales.add(sale);
	}
}
