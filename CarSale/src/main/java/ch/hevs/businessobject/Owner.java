package ch.hevs.businessobject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Owner")
public class Owner extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToMany(mappedBy = "owner")
    private List<Car> cars;

    //attributes
    private String IBAN;

    public Owner() {
        this.cars = new ArrayList<Car>();
    }

    public Owner(String firstname, String lastname, String address, String phone, String email,Date birthdate, String IBAN) {
        super(firstname, lastname, address, phone, email, birthdate);
        this.IBAN = IBAN;
        this.cars = new ArrayList<Car>();
    }

    /**
     * Set the id of the owner.
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the list of cars of the owner.
     * @return List<Car> return the cars
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * Set the list of cars of the owner.
     * @param cars the cars to set
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    /**
     * Add a car to the list of cars of the owner.
     * @param car
     */
    public void addCar(Car car) {
        this.cars.add(car);
        car.setOwner(this);
    }


    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the IBAN
     */
    public String getIBAN() {
        return IBAN;
    }

    /**
     * @param IBAN the IBAN to set
     */
    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    @Override
    public String toString() {
        return this.getFullName();
    }
}
