package ch.hevs.businessobject;

import jakarta.persistence.*;

@Entity
@Table(name = "Sale")
public class Sale {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Car car;

    @ManyToOne
    private Buyer buyer;

    //attributes
    private String date;
    private String price;
    private String paymentMethod;
    private String paymentStatus;

    public Sale() {
    }

    public Sale(Car car, Buyer buyer, String date, String price, String paymentMethod, String paymentStatus) {
        this.car = car;
        this.buyer = buyer;
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
     * Get the date of the sale.
     * @return String return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of the sale.
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the price of the sale.
     * @return String return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Set the price of the sale.
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Get the payment method of the sale.
     * @return String return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Set the payment method of the sale.
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Get the payment status of the sale.
     * @return String return the paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Set the payment status of the sale.
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
