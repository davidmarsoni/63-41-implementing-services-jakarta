package ch.hevs.carsaleservice;

import java.math.BigDecimal;
import java.util.List;

import ch.hevs.businessobject.Buyer;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.CarBrand;
import ch.hevs.businessobject.Owner;
import ch.hevs.businessobject.Sale;
import jakarta.ejb.Local;

@Local
public interface CarSale{

    String test();

    List<CarBrand> getCarBrands();
    CarBrand getCarBrand(Long id);
    CarBrand getCarBrand(String carbrandName);
    
    List<Car> getCars();
    List<Car> getCars(Long ownerID);
    List<Car> getFilterdCars(Long carBrandId,String model, int min_year_of_construction, int max_year_of_construction, int min_kilometers, int max_kilometers, String fuel, String color, BigDecimal min_price, BigDecimal max_price);
    Car getCar(Long id);

    List<Sale> getSalesByOwner(Long ownerId);
    List<Sale> getSalesByBuyer(Long buyerId);
    List<Sale> getSalesByCar(Long carId);

    List<Owner> getOwners();
    Owner getOwner(Long id);

    List<Buyer> getBuyers();
    Buyer getBuyer(Long id);

    String addCar(Long carBrandId,String model, int yearOfConstruction, int kilometers, String fuel, String color, String description, BigDecimal price, boolean isAvailable, Long ownerId);
    String updateCar(Long selectedCarId, Long carBrandId, String model, int year_of_construction, int kilometers,
            String soucefuel, String color, String description, BigDecimal price, boolean isAvailable,
            Long ownerId);
    String removeCar(Long carId);
    String buyCar(Long carId, Long buyerId, BigDecimal price);

    String acceptSale(long saleId);
    String refuseSale(long saleId);
}
