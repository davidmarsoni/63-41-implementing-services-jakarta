package ch.hevs.carsaleservice;

import ch.hevs.businessobject.Car;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class SaleBean implements Sale{
    @Override
    public List<Car> getCars() {
        return null;
    }

    @Override
    public List<Sale> getPreviousSales() {
        return null;
    }
}
