package ch.hevs.carsaleservice;

import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.CarBrand;

import java.util.List;

public interface CarSale{

    List<Car> getCars();

    List<CarSale> getPreviousSales();

    List<CarBrand> getCarBrands();

}
