package ch.hevs.carsaleservice;

import java.math.BigDecimal;
import java.util.List;

import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.CarBrand;
import ch.hevs.businessobject.Owner;
import jakarta.ejb.Local;

@Local
public interface CarSale{

    CarBrand getCarBrand(String carbrandName);
    CarBrand getCarBrand(Long id);
    String test();
    List<CarBrand> getCarBrands();
    List<Car> getCars(Long ownerID);
    Car getCar(Long id);
    List<Owner> getOwners();
    Owner getOwner(Long id);

    String addCar(Long carBrandId,String model, int yearOfConstruction, int kilometers, String fuel, String color, String description, BigDecimal price, boolean isAvailable, Long ownerId);
    String removeCar(Long carId);
    String updateCar(Long selectedCarId, Long carBrandId, String model, int year_of_construction, int kilometers,
            String soucefuel, String color, String description, BigDecimal price, boolean isAvailable,
            Long ownerId);
}
