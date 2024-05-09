package ch.hevs.businessobject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Owner")
public class Owner extends Person {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    private List<Car> cars;

    public Owner() {
        this(null, null, null, null, null,null);
    }

    public Owner(String firstname, String lastname, String address, String phone, String email,Date birthdate) {
        super(firstname, lastname, address, phone, email, birthdate);
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
     * Set the id of the owner.
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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

}
