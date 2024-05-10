package ch.hevs.carsaleservice;

import ch.hevs.businessobject.CarBrand;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;


@Stateless
public class CarSaleBean implements CarSale{
    @PersistenceContext(unitName = "carSalePU", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

    @Override
    public CarBrand getCarBrand(String carbrandName) {
        
        Query query = em.createQuery("FROM CarBrand c WHERE c.name = :name");
        query.setParameter("name", carbrandName);
        CarBrand carBrand = (CarBrand) query.getSingleResult();
        return carBrand;
    }

    @Override
    public String test() {
        return "Hello from CarSaleBean";
    }

}
