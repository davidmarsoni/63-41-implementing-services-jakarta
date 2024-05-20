package ch.hevs.businessobject;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne()
    private Car car;

    @ManyToOne()
    private Buyer buyer;

    @ManyToOne()
    private Owner owner;

    //attributes
    private LocalDate date;
    private BigDecimal price;
    private String paymentMethod;
    private PaymentStatus paymentStatus;

    public Sale() {
    }

    public Sale(Car car, Buyer buyer,Owner owner, LocalDate date, BigDecimal price, String paymentMethod, PaymentStatus paymentStatus) {
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
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
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
