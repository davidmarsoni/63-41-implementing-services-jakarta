package ch.hevs.carsaleservice;

import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.CarBrand;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

import java.util.List;

@Stateless
public class CarSaleBean implements CarSale{
    @PersistenceContext(unitName = "carSalePU", type=PersistenceContextType.EXTENDED)
	private EntityManager em;

    @Override
    public List<Car> getCars() {
        return null;
    }

    @Override
    public List<CarSale> getPreviousSales() {
        return null;
    }

    @Override
    public List<CarBrand> getCarBrands() {
        return (List<CarBrand>) em.createQuery("SELECT cb.carBrand FROM CarBrand cb").getResultList();
    }

    @Override
    public String Test() {
        return "Test";
    }

}
