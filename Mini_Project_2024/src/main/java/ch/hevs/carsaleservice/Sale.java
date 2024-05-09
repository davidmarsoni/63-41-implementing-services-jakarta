package ch.hevs.carsaleservice;

import ch.hevs.businessobject.Car;

import java.util.List;

public interface Sale {

    List<Car> getCars();

    List<Sale> getPreviousSales();

}
