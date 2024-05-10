package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "CarBrand")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    //relations
    @OneToMany(mappedBy = "carBrand")
    private List<Car> cars;

    //attributes
    private String name;
    @Column(nullable = true)
    private String country;
    @Column(nullable = true)
    private String website;
    @Column(nullable = true)
    private String description;

    public CarBrand() {
        this(null, null, null, null);
    }
    
    /**
     * Constructor for creating a CarBrand object with only the name.
     * 
     * @param name the name of the car brand
     */
    public CarBrand(String name) {
        this(name, null, null, null);
    }
    
    /**
     * Constructor for creating a CarBrand object with all attributes.
     * 
     * @param name        the name of the car brand
     * @param country     the country of origin of the car brand
     * @param website     the website of the car brand
     * @param description the description of the car brand
     */
    public CarBrand(String name, String country, String website, String description) {
        this.name = name;
        this.country = country;
        this.website = website;
        this.description = description;
        this.cars = new ArrayList<Car>();
    }

    /**
     * Get the ID of the car brand.
     * 
     * @return the ID of the car brand
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the name of the car brand.
     * 
     * @return the name of the car brand
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the car brand.
     * 
     * @param name the name of the car brand
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the country of origin of the car brand.
     * 
     * @return the country of origin of the car brand
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the country of origin of the car brand.
     * 
     * @param country the country of origin of the car brand
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the website of the car brand.
     * 
     * @return the website of the car brand
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Set the website of the car brand.
     * 
     * @param website the website of the car brand
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Get the description of the car brand.
     * 
     * @return the description of the car brand
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the car brand.
     * 
     * @param description the description of the car brand
     */
    public void setDescription(String description) {
        this.description = description;
    }

    

    /**
     * @return List<Car> return the cars
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * @param cars the cars to set
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

}
