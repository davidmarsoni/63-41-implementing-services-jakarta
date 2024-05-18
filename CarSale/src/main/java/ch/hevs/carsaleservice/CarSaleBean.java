package ch.hevs.carsaleservice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import ch.hevs.businessobject.Buyer;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.CarBrand;
import ch.hevs.businessobject.Owner;
import ch.hevs.businessobject.PaymentStatus;
import ch.hevs.businessobject.Sale;
import ch.hevs.businessobject.TypeOfFuel;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
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
    public String test() {
        return "Hello from CarSaleBean";
    }

    @Override
    public List<Car> getCars() {
        Query query = em.createQuery("FROM Car", Car.class);
        List<Car> cars = query.getResultList();
        return cars;
    }

    @Override
    public List<Car> getCars(Long ownerID) {
        Query query = em.createQuery("FROM Car c WHERE c.owner.id =:id");
        query.setParameter("id", ownerID);
        List<Car> cars = query.getResultList();
        return cars;
    }

    @Override
    public List<Car> getFilterdCars(Long carBrandId, String model, int min_year_of_construction,
            int max_year_of_construction, int min_kilometers, int max_kilometers, String fuel, String color,
            BigDecimal min_price, BigDecimal max_price) {
        //if the parameters are null, don't filter by them and by default return all the cars that are available
        String queryStr = "FROM Car c WHERE 1=1 and c.isAvailable = true";

        if (carBrandId != null) {
            queryStr += " and c.carBrand.id =:carBrandId";
        }
        if (model != null && !model.isEmpty()) {
            queryStr += " and c.model like :model";
        }
        if (min_year_of_construction > 0) {
            queryStr += " and c.year_of_construction >=:min_year_of_construction";
        }
        if (max_year_of_construction > 0) {
            queryStr += " and c.year_of_construction <=:max_year_of_construction";
        }
        if (min_kilometers > 0) {
            queryStr += " and c.kilometers >=:min_kilometers";
        }

        if (max_kilometers > 0) {
            queryStr += " and c.kilometers <=:max_kilometers";
        }
        if (fuel != null && !fuel.isEmpty()) {
            queryStr += " and c.fuel =:fuel";
        }
        if (color != null && !color.isEmpty()) {
            queryStr += " and c.color like :color";
        }
        if (min_price != null) {
            queryStr += " and c.price >=:min_price";
        }
        if (max_price != null) {
            queryStr += " and c.price <=:max_price";
        }

        Query query = em.createQuery(queryStr, Car.class);

        if (carBrandId != null) {
            query.setParameter("carBrandId", carBrandId);
        }
        if (model != null && !model.isEmpty()) {
            query.setParameter("model", model);
        }
        if (min_year_of_construction > 0) {
            query.setParameter("min_year_of_construction", min_year_of_construction);
        }
        if (max_year_of_construction > 0) {
            query.setParameter("max_year_of_construction", max_year_of_construction);
        }
        if (min_kilometers > 0) {
            query.setParameter("min_kilometers", min_kilometers);
        }
        if (max_kilometers > 0) {
            query.setParameter("max_kilometers", max_kilometers);
        }
        if (fuel != null && !fuel.isEmpty()) {
            query.setParameter("fuel", TypeOfFuel.valueOf(fuel));
        }
        if (color != null && !color.isEmpty()) {
            query.setParameter("color", color);
        }
        if (min_price != null) {
            query.setParameter("min_price", min_price);
        }
        if (max_price != null) {
            query.setParameter("max_price", max_price);
        }

        List<Car> cars = query.getResultList();
        return cars;
    }

    @Override
    public Car getCar(Long id) {
        return em.find(Car.class, id);
    }

    @Override
    public List<CarBrand> getCarBrands() {
        Query query = em.createQuery("FROM CarBrand", CarBrand.class);
        List<CarBrand> carBrands = query.getResultList();
        return carBrands;
    }

    @Override
    public CarBrand getCarBrand(Long id) {
        return em.find(CarBrand.class, id);
    }

    @Override
    public CarBrand getCarBrand(String carbrandName) {
        Query query = em.createQuery("FROM CarBrand c WHERE c.name =:name");
        query.setParameter("name", carbrandName);
        return (CarBrand) query.getSingleResult();
    }

    @Override
    public List<Sale> getSalesByOwner(Long ownerId) {
        Query query = em.createQuery("FROM Sale s WHERE s.car.owner.id =:id");
        query.setParameter("id", ownerId);
        List<Sale> sales = query.getResultList();
        return sales;
    }

    @Override
    public List<Sale> getSalesByBuyer(Long buyerId) {
        Query query = em.createQuery("FROM Sale s WHERE s.buyer.id =:id");
        query.setParameter("id", buyerId);
        List<Sale> sales = query.getResultList();
        return sales;
    }
    @Override
    public List<Sale> getSalesByCar(Long carId) {
        Query query = em.createQuery("FROM Sale s WHERE s.car.id =:id");
        query.setParameter("id", carId);
        List<Sale> sales = query.getResultList();
        return sales;
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
    public List<Buyer> getBuyers() {
        Query query = em.createQuery("FROM Buyer", Buyer.class);
        List<Buyer> buyers = query.getResultList();
        return buyers;
    }

    @Override
    public Buyer getBuyer(Long id) {
        return em.find(Buyer.class, id);
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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String buyCar(Long carId, Long buyerId, BigDecimal price) {
        if(price == null) {
            return "Price is missing";
        }
        if(price.compareTo(BigDecimal.ZERO) <= 0) {
            return "Price must be greater than 0";
        }
    
        //verify if the car and the buyer exist
        Car car = em.find(Car.class, carId);
        if (car == null) {
            return "Car not found";
        }
        Buyer buyer = em.find(Buyer.class, buyerId);
        if (buyer == null) {
            return "Buyer not found";
        }

        //if the price is lower than 80% of the car price, return an error message
        if(price.compareTo(car.getPrice().multiply(new BigDecimal(0.8))) < 0) {
            return "Price is too low. The minimum price for this car is " + car.getPrice().multiply(new BigDecimal(0.8)).setScale(0, RoundingMode.HALF_UP);
        }

        // create a new sale for the car
        Sale sale = new Sale();
        sale.setCar(car);
        sale.setBuyer(buyer);
        sale.setPrice(price);
        sale.setPaymentMethod("Credit Card");
        sale.setPaymentStatus(PaymentStatus.PENDING);
        sale.setDate(java.time.LocalDate.now());
        //set the car as not available 
        car.setAvailable(false);

        // persist the sale and merge the car
        em.persist(sale);
        em.merge(car);

        return "User " + buyer.getFullName()+ "ask for buying the car" + car.getModel() + " for the price of " + price;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String acceptSale(long saleId)
    {
        Query query = em.createQuery("FROM Sale s WHERE s.id =:id");
        query.setParameter("id", saleId);
        Sale sale = (Sale) query.getSingleResult();

        if (sale == null) {
            return "Sale not found";
        }
        // set the payment status to PAID
        sale.setPaymentStatus(PaymentStatus.ACCEPTED);

        //modify the car to be not available anymore and don't have a owner
        Car car = sale.getCar();
        car.setOwner(null);

        em.merge(sale);
        em.merge(car);

        return "Sale confirmed";
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String refuseSale(long saleId) {
        Query query = em.createQuery("FROM Sale s WHERE s.id =:id");
        query.setParameter("id", saleId);
        Sale sale = (Sale) query.getSingleResult();

        if (sale == null) {
            return "Sale not found";
        }
        // set the payment status to REFUSED
        sale.setPaymentStatus(PaymentStatus.REFUSED);

        //modify the car to be available again
        Car car = sale.getCar();
        car.setAvailable(true);

        em.merge(sale);
        em.merge(car);

        return "Sale refused";
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
}
