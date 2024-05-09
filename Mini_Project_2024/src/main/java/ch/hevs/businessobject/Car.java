package ch.hevs.businessobject;

import java.text.DecimalFormat;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    //relations
    @ManyToOne()
    private CarBrand carBrand;

    @ManyToOne()
    private Owner owner;

    @OneToOne(mappedBy = "car")
    private Sale sale;

    //attributes
    private String model;
    private int year_of_construction;
    private int Kilometers;
    private TypeOfFuel fuel;
    @ElementCollection()
    private List<String> colors;
    @ElementCollection()
    private List<String> options;
    private DecimalFormat price;
    private boolean isAvailable;

    /**
     * Constructor for creating a Car object with only the model.
     */
    public Car() {
    }

    /**
     * Constructor for creating a Car object with only the model.
     * 
     * @param model the model of the car
     * @param year_of_construction the year of construction of the car
     * @param Kilometers the number of kilometers driven by the car
     * @param fuel the type of fuel used by the car
     * @param price the price of the car
     * @param isAvailable the availability of the car
     */
    public Car(String model, int year_of_construction, int Kilometers, TypeOfFuel fuel, DecimalFormat price, boolean isAvailable) {
        this.model = model;
        this.year_of_construction = year_of_construction;
        this.Kilometers = Kilometers;
        this.fuel = fuel;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    /**
     * Constructor for creating a Car object with only the model.
     * 
     * @param model the model of the car
     */
    /**
     * Constructor for creating a Car object with all attributes.
     * 
     * @param id                  the ID of the car
     * @param model               the model of the car
     * @param year_of_construction the year of construction of the car
     * @param Kilometers          the number of kilometers driven by the car
     * @param fuel                the type of fuel used by the car
     * @param colors              the available colors of the car
     * @param options             the available options of the car
     * @param price               the price of the car
     * @param isAvailable         the availability of the car
     */
    public Car(Long id, String model, int year_of_construction, int Kilometers, TypeOfFuel fuel, List<String> colors,
            List<String> options, DecimalFormat price, boolean isAvailable) {
        this.id = id;
        this.model = model;
        this.year_of_construction = year_of_construction;
        this.Kilometers = Kilometers;
        this.fuel = fuel;
        this.colors = colors;
        this.options = options;
        this.price = price;
        this.isAvailable = isAvailable;
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
        return Kilometers;
    }

    /**
     * Set the number of kilometers driven by the car.
     * 
     * @param Kilometers the number of kilometers to set
     */
    public void setKilometers(int Kilometers) {
        this.Kilometers = Kilometers;
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
     * Get the available colors of the car.
     * 
     * @return List<String> the available colors of the car
     */
    public List<String> getColors() {
        return colors;
    }

    /**
     * Set the available colors of the car.
     * 
     * @param colors the available colors to set
     */
    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    /**
     * Add a color to the available colors of the car.
     * @return void
     */
    public void addColor(String color) {
        colors.add(color);
    }

    /**
     * Get the available options of the car.
     * 
     * @return List<String> the available options of the car
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Set the available options of the car.
     * 
     * @param options the available options to set
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * Add an option to the available options of the car.
     * @return void
     */
    public void addOption(String option) {
        options.add(option);
    }

    /**
     * Get the price of the car.
     * 
     * @return DecimalFormat the price of the car
     */
    public DecimalFormat getPrice() {
        return price;
    }

    /**
     * Set the price of the car.
     * 
     * @param price the price to set
     */
    public void setPrice(DecimalFormat price) {
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
}
