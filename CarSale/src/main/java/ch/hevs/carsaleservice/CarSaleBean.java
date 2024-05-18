package ch.hevs.carsaleservice;

import java.math.BigDecimal;
import java.util.List;

import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.CarBrand;
import ch.hevs.businessobject.Owner;
import ch.hevs.businessobject.TypeOfFuel;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;


@Stateless
public class CarSaleBean implements CarSale{
    @PersistenceContext(unitName = "carSalePU", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

    @Override
    public CarBrand getCarBrand(String carbrandName) {
        Query query = em.createQuery("FROM CarBrand c WHERE c.name =:name");
        query.setParameter("name", carbrandName);
        return (CarBrand) query.getSingleResult();
    }

    @Override
    public CarBrand getCarBrand(Long id) {
        return em.find(CarBrand.class, id);
    }

    @Override
    public List<CarBrand> getCarBrands() {
        Query query = em.createQuery("FROM CarBrand", CarBrand.class);
        List<CarBrand> carBrands = query.getResultList();
        return carBrands;
    }

    @Override
    public String test() {
        return "Hello from CarSaleBean";
    }

    @Override
    public List<Owner> getOwners() {
        Query query = em.createQuery("FROM Owner", Owner.class);
        List<Owner> owners = query.getResultList();
        return owners;
    }

    @Override
    public Owner getOwner(Long id) {
        return em.find(Owner.class, id);
    }

    

    private Owner getOwner(Owner ownerString) {
        Query query = em.createQuery("FROM Owner o WHERE o.firstName =:firstName AND o.lastName =:lastName");
        query.setParameter("firstName", ownerString);
        query.setParameter("lastName", ownerString);
        return (Owner) query.getSingleResult();
        
    }

    @Override
    public List<Car> getCars(Long ownerID) {
        Query query = em.createQuery("FROM Car c WHERE c.owner.id =:id");
        query.setParameter("id", ownerID);
        List<Car> cars = query.getResultList();
        return cars;
    }

    @Override
    public String removeCar(Long carID) {
        Car car = em.find(Car.class, carID);
        if (car == null) {
            return "Car not found";
        }
        em.remove(car);
        return "Car successfully removed";
        
    }

    @Override
    public Car getCar(Long id) {
        return em.find(Car.class, id);
    }

    @Override
    public String updateCar(Long selectedCar, Long sourceCarBrands, String model, int year_of_construction,
            int kilometers, String soucefuel, String color, String description, BigDecimal price, boolean isAvailable,
            Long sourceOwner) {
        String message = verifyCarParameters(sourceCarBrands, model, year_of_construction, kilometers, soucefuel, color, description, price, isAvailable, sourceOwner);
        if (message != null) {
            return message;
        }
        Car car = em.find(Car.class, selectedCar);
        if (car == null) {
            return "Car not found";
        }
        CarBrand tmpCarBrand = getCarBrand(sourceCarBrands);
        if (tmpCarBrand == null) {
            return "Car brand not found";
        }
        Owner owner = getOwner(sourceOwner);
        if (owner == null) {
            return "Owner not found";
        }
        TypeOfFuel tmpFuel = TypeOfFuel.valueOf(soucefuel);

        car.setCarBrand(tmpCarBrand);
        car.setModel(model);
        car.setYear_of_construction(year_of_construction);
        car.setKilometers(kilometers);
        car.setFuel(tmpFuel);
        car.setColor(color);
        car.setDescription(description);
        car.setPrice(price);
        car.setAvailable(isAvailable);
        car.setOwner(owner);

        em.merge(car);
        em.flush();

        return "";

    }

    private String verifyCarParameters(Long sourceCarBrands, String model, int year_of_construction, int kilometers, String soucefuel, String color, String description, BigDecimal price, boolean isAvailable, Long sourceOwner) {
        if (sourceCarBrands == null) {
            return "Car brand is missing";
        }
        if (model == null || model.isEmpty()) {
            return "Model is missing";
        }
        if (year_of_construction <= 1850){
            return "Year of construction must be greater than 1850";
        }
        if (kilometers < 0) {
            return "Negative kilometers are not allowed";
        }
        if (soucefuel == null || soucefuel.isEmpty()) {
            return "Fuel is missing";
        }
        if (price == null) {
            return "Price is missing";
        }
        if(sourceOwner == null) {
            return "Owner is missing";
        }
        return null;
    }

    @Override
    public String addCar(Long carBrandId, String model, int yearOfConstruction, int kilometers, String fuel,
            String color, String description, BigDecimal price, boolean isAvailable, Long ownerId) {
        // verify all the parameters and return a String with the error message
        String message = verifyCarParameters(carBrandId, model, yearOfConstruction, kilometers, fuel, color, description, price, isAvailable, ownerId);
        if (message != null) {
            return message;
        }
        
        try {
            CarBrand carBrand = getCarBrand(carBrandId);
            if(carBrand == null) {
                return "Car brand not found";
            }
            Owner owner = getOwner(ownerId);
            if(owner == null) {
                return "Owner not found";
            }
            TypeOfFuel tmpFuel = TypeOfFuel.valueOf(fuel);

            
            Car car = new Car(carBrand,model,yearOfConstruction,kilometers,tmpFuel,price,isAvailable, color, description);
            
            car.setOwner(owner);
            em.persist(car);
            
            return "";
        } catch (NoResultException e) {
            return "Car brand or owner not found";
        }
    }
}
