package ch.hevs.businessobject;
import java.math.BigDecimal;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a car.
 */
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    //relations
    @ManyToOne() // no cascade here
    private CarBrand carBrand;

    @ManyToOne() // no cascade here
    private Owner owner; 

    @OneToMany(mappedBy = "car") // no cascade here 
    private List<Sale> sales;

    //attributes
    private String model;
    private int year_of_construction;
    private int kilometers;
    private TypeOfFuel fuel;
    @Column(nullable = true)
    private String color;
    @Column(nullable = true)
    private String description;
    private BigDecimal price;
    private boolean isAvailable;

    /**
     * Constructor for creating a Car object with only the model.
     */
    public Car() {
        this.sales = new ArrayList<Sale>();
    }

    /**
     * Constructor for creating a Car object with only the model.
     * 
     * @param model the model of the car
     * @param year_of_construction the year of construction of the car
     * @param kilometers the number of kilometers driven by the car
     * @param fuel the type of fuel used by the car
     * @param color the available color of the car
     * @param price the price of the car
     * @param isAvailable the availability of the car
     */
    public Car(CarBrand carBrand, String model, int year_of_construction, int kilometers, TypeOfFuel fuel, BigDecimal price, boolean isAvailable) {
        this.carBrand = carBrand;
        this.model = model;
        this.year_of_construction = year_of_construction;
        this.kilometers = kilometers;
        this.fuel = fuel;
        this.price = price;
        this.isAvailable = isAvailable;
        this.sales = new ArrayList<Sale>();
    }

    /**
     * Constructor for creating a Car object with all attributes.
     * @param carBrand the car brand of the car
     * @param model the model of the car
     * @param year_of_construction the year of construction of the car
     * @param kilometers the number of kilometers driven by the car
     * @param fuel the type of fuel used by the car
     * @param price the price of the car
     * @param isAvailable the availability of the car
     * @param color the available color of the car
     * @param description the available description of the car
     */
    public Car(CarBrand carBrand, String model, int year_of_construction, int kilometers, TypeOfFuel fuel, BigDecimal price, boolean isAvailable, String color, String description) {
        this.carBrand = carBrand;
        this.model = model;
        this.year_of_construction = year_of_construction;
        this.kilometers = kilometers;
        this.fuel = fuel;
        this.price = price;
        this.isAvailable = isAvailable;
        this.color = color;
        this.description = description;
    }

    /**
     * Get the ID of the car.
     * 
     * @return Long the ID of the car
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the model of the car.
     * 
     * @return String the model of the car
     */
    public String getModel() {
        return model;
    }

    /**
     * Set the model of the car.
     * 
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get the year of construction of the car.
     * 
     * @return int the year of construction of the car
     */
    public int getYear_of_construction() {
        return year_of_construction;
    }

    /**
     * Set the year of construction of the car.
     * 
     * @param year_of_construction the year of construction to set
     */
    public void setYear_of_construction(int year_of_construction) {
        this.year_of_construction = year_of_construction;
    }

    /**
     * Get the number of kilometers driven by the car.
     * 
     * @return int the number of kilometers driven by the car
     */
    public int getKilometers() {
        return kilometers;
    }

    /**
     * Set the number of kilometers driven by the car.
     * 
     * @param Kilometers the number of kilometers to set
     */
    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    /**
     * Get the type of fuel used by the car.
     * 
     * @return TypeOfFuel the type of fuel used by the car
     */
    public TypeOfFuel getFuel() {
        return fuel;
    }

    /**
     * Set the type of fuel used by the car.
     * 
     * @param fuel the type of fuel to set
     */
    public void setFuel(TypeOfFuel fuel) {
        this.fuel = fuel;
    }

    /**
     * Get the available color of the car.
     * 
     * @return String the available color of the car
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the available color of the car.
     * 
     * @param color the available color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get the available description of the car.
     *
     * @return String the available description of the car
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the available description of the car.
     *
     * @param description the available description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the price of the car.
     * 
     * @return DecimalFormat the price of the car
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Set the price of the car.
     * 
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Check if the car is available.
     * 
     * @return boolean true if the car is available, false otherwise
     */
    public boolean isIsAvailable() {
        return isAvailable;
    }

    /**
     * Set the availability of the car.
     * 
     * @param isAvailable the availability to set
     */
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * Get the car brand of the car.
     * 
     * @return CarBrand the car brand of the car
     */
    public CarBrand getCarBrand() {
        return carBrand;
    }

    /**
     * Set the car brand of the car.
     * 
     * @param carBrand the car brand to set
     */
    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    /**
     * Get the owner of the car.
     * 
     * @return Owner the owner of the car
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Set the owner of the car.
     * 
     * @param owner the owner to set
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }


    /**
     * Get the availability of the car.
     *
     * @return boolean the availability of the car
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Set the availability of the car.
     *
     * @param available the availability to set
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return List<Sale> return the sales
     */
    public List<Sale> getSales() {
        return sales;
    }

    /**
     * @param sales the sales to set
     */
    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    /**
     * Add a sale to the list of sales of the car.
     * 
     * @param sale the sale to add
     */
    public void addSale(Sale sale) {
        this.sales.add(sale);
    }
}
