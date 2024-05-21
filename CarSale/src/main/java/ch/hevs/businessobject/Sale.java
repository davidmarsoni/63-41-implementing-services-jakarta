package ch.hevs.businessobject;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.*;

/**
 * Represents a sale.
 */
@Entity
@Table(name = "Sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne() // no cascade here
    private Car car;
 
    @ManyToOne() // no cascade here
    private Buyer buyer;

    @ManyToOne() // no cascade here
    private Owner owner;

    //attributes
    private Date date;
    private BigDecimal price;
    private String paymentMethod;
    private PaymentStatus paymentStatus;

    /**
     * Constructs a new Sale object.
     */
    public Sale() {
    }

    /**
     * Constructs a new Sale object with the specified details.
     * @param car the car of the sale
     * @param buyer the buyer of the sale
     * @param owner the owner of the sale
     * @param date the date of the sale
     * @param price the price of the sale
     * @param paymentMethod the payment method of the sale
     * @param paymentStatus the payment status of the sale
     */
    public Sale(Car car, Buyer buyer,Owner owner, Date date, BigDecimal price, String paymentMethod, PaymentStatus paymentStatus) {
        this.car = car;
        this.buyer = buyer;
        this.owner = owner;
        this.date = date;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    /**
     * Get the ID of the sale.
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the car of the sale.
     * @return Car return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Set the car of the sale.
     * @param car the car to set
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Get the buyer of the sale.
     * @return Buyer return the buyer
     */
    public Buyer getBuyer() {
        return buyer;
    }

    /**
     * Set the buyer of the sale.
     * @param buyer the buyer to set
     */
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return LocalDate return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return BigDecimal return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return String return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @return PaymentStatus return the paymentStatus
     */
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    /**
     * @return Owner return the owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}
