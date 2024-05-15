package ch.hevs.carsaleservice;

import java.math.BigDecimal;
import java.util.List;

import ch.hevs.businessobject.CarBrand;
import ch.hevs.businessobject.Owner;
import jakarta.ejb.Local;

@Local
public interface CarSale{

    CarBrand getCarBrand(String carbrandName);
    CarBrand getCarBrand(Long id);
    String test();
    List<CarBrand> getCarBrands();
    List<Owner> getOwners();
    Owner getOwner(Long id);

    public boolean addCar(String carBrand,String model, int yearOfConstruction, int kilometers, String fuel, String color, String description, BigDecimal price, boolean isAvailable, String owner);
}
