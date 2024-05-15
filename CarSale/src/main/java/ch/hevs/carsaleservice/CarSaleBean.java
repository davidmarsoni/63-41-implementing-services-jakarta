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

    @Override
    public boolean addCar(String carBrandString, String model, int yearOfConstruction, int kilometers, String fuel, String color,
            String description, BigDecimal price, boolean isAvailable, String ownerString) {
        try {
            CarBrand tmpCarBrand = getCarBrand(Long.parseLong(carBrandString));
            Owner owner = getOwner(Long.parseLong(ownerString));
            TypeOfFuel tmpFuel = TypeOfFuel.valueOf(fuel);
            
            Car car = new Car(tmpCarBrand,model,yearOfConstruction,kilometers,tmpFuel,price,isAvailable, color, description);
            
            car.setOwner(owner);
            em.persist(car);
            
            return true;
        } catch (NoResultException e) {
            return false;
        }
       
    }

    private Owner getOwner(Owner ownerString) {
        Query query = em.createQuery("FROM Owner o WHERE o.firstName =:firstName AND o.lastName =:lastName");
        query.setParameter("firstName", ownerString);
        query.setParameter("lastName", ownerString);
        return (Owner) query.getSingleResult();
        
    }

    

}
