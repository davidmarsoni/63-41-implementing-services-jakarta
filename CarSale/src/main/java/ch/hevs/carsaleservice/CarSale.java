package ch.hevs.carsaleservice;

import ch.hevs.businessobject.CarBrand;
import jakarta.ejb.Local;

@Local
public interface CarSale{

    CarBrand getCarBrand(String carbrandName);
    String test();
}
