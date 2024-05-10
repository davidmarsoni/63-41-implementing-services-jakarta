package ch.hevs.carsaleservice;

import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.CarBrand;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface CarSale{

    List<Car> getCars();

    List<CarSale> getPreviousSales();

    List<CarBrand> getCarBrands();

    String Test();

}
