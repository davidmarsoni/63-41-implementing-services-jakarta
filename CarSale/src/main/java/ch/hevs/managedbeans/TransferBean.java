package ch.hevs.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.CarBrand;
import ch.hevs.businessobject.Owner;
import ch.hevs.businessobject.TypeOfFuel;
import ch.hevs.carsaleservice.CarSale;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

/**
 * TransferBean.java
 * 
 */

@ManagedBean
@SessionScoped
@Named("transferBean")
public class TransferBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private CarSale carSale;
    // === carManagment ===

    private Long selectedCar;
    // list of cars owned by the owner
    private List<Car> cars;
    private Long selectedOwner;

    // list of car brands and the selected car brand
    private List<CarBrand> carBrands;
    private Long sourceCarBrands;

    // list of type of fuel and the selected type of fuel
    private List<TypeOfFuel> fuelOptions;
    private String soucefuel;

    // list of owners and the selected owner
    private List<Owner> owners;
    private Long sourceOwner;

    // property for the car
    private String model;
    private int year_of_construction;
    private int kilometers;
    private String color;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;

    private String info;
    private String formState;
    

    // === car buy ===
    //filtered list of car based on the brand selected


    @PostConstruct
    public void initialize() throws NamingException {

        // use JNDI to inject reference to bank EJB
        InitialContext ctx = new InitialContext();
        carSale = (CarSale) ctx.lookup("java:global/CarSale-0.0.1-SNAPSHOT/CarSaleBean!ch.hevs.carsaleservice.CarSale");
        // get the list of car brands
        carBrands = carSale.getCarBrands();
        owners = carSale.getOwners();
        // get all the choices of the enum TypeOfFuel
        fuelOptions = List.of(TypeOfFuel.values());
    }

    // === preloading ===
    public void manageCarPreload() {
        if(getFormState() == null){
            setFormState("Add");
        }

        if(selectedOwner == null){
            selectedOwner = owners.get(0).getId();
        }
        updateCarList();
    }
 
    public void buyCarPreload() {
        setInfo("Hello from buyCarPreload");
    }

    // === car management ===
    public void addUpdateCar() {
        String message = "";
        if(selectedCar == null) {
            message = carSale.addCar(sourceCarBrands, model, year_of_construction, kilometers, soucefuel, color,
                description, price, isAvailable, selectedOwner);
        } else {
            message = carSale.updateCar(selectedCar, sourceCarBrands, model, year_of_construction, kilometers, soucefuel, color,
                description, price, isAvailable, selectedOwner);
        }

        if(message.isEmpty() && selectedCar == null) {
            message = "Car successfully added";
        }
        if(message.isEmpty() && selectedCar != null) {
            message = "Car successfully updated";
        }
        updateCarList();
        setInfo(message);//+" : ("+model+" "+year_of_construction+" "+kilometers+" "+soucefuel+" "+color+" "+description+" "+price+" "+isAvailable+" "+selectedOwner+")");
        if(message.equals("Car successfully added") || message.equals("Car successfully updated")) {
            resetFields();
        }
        
    }

    private void resetFields() {
        model = "";
        year_of_construction = 0;
        kilometers = 0;
        color = "";
        description = "";
        price = new BigDecimal(0);
        isAvailable = false;
        sourceCarBrands = carBrands.get(0).getId();
        soucefuel = fuelOptions.get(0).toString();
        selectedCar = null;
        setFormState(null);
    }

    public void removeCar(Car car) {
        String message = carSale.removeCar(car.getId());
        updateCarList();
        setInfo(message);
    }

    public void updateSourceOwner(ValueChangeEvent event) {
        // get the new value from the event
        selectedOwner = (Long) event.getNewValue();
        // update the list of cars owned by the owner
        updateCarList();
        // set the info message
        setInfo("Owner selected: " + carSale.getOwner(getSelectedOwner()).getFullName());
    }

    public void updateCarList() {
        cars = carSale.getCars(selectedOwner);
    }
    public void prepareEditCar(Car car) {
        Car carToEdit = carSale.getCar(car.getId());

        if(carToEdit == null) {
            setInfo("Car not found");
            return;
        }

        model = carToEdit.getModel();
        year_of_construction = carToEdit.getYear_of_construction();
        kilometers = carToEdit.getKilometers();
        color = carToEdit.getColor();
        description = carToEdit.getDescription();
        price = carToEdit.getPrice();
        isAvailable = carToEdit.isIsAvailable();
        // set the selected car brand
        sourceCarBrands = carToEdit.getCarBrand().getId();
        // set the selected type of fuel
        soucefuel = carToEdit.getFuel().toString();

        selectedCar = carToEdit.getId();
        selectedOwner = carToEdit.getOwner().getId();
        setFormState("Edit");
        setInfo("Selected car successfully loaded for editing");

    }

    // === car buy ===

    
    // === test ===
    public String test() {
        return carSale.test();
    }

    // === getters and setters ===
    //#region getters and setters

    /**
     * @return CarSale return the carSale
     */
    public CarSale getCarSale() {
        return carSale;
    }

    /**
     * @param carSale the carSale to set
     */
    public void setCarSale(CarSale carSale) {
        this.carSale = carSale;
    }

    /**
     * @param carBrands the carBrands to set
     */
    public void setCarBrands(List<CarBrand> carBrands) {
        this.carBrands = carBrands;
    }

    /**
     * @return List<CarBrand> return the carBrands
     */
    public List<CarBrand> getCarBrands() {
        return carBrands;
    }

    /**
     * @return String return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return int return the year_of_construction
     */
    public int getYear_of_construction() {
        return year_of_construction;
    }

    /**
     * @param year_of_construction the year_of_construction to set
     */
    public void setYear_of_construction(int year_of_construction) {
        this.year_of_construction = year_of_construction;
    }

    /**
     * @return int return the kilometers
     */
    public int getKilometers() {
        return kilometers;
    }

    /**
     * @param kilometers the kilometers to set
     */
    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    /**
     * @return String return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return BigDecimal return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return boolean return the isAvailable
     */
    public boolean isIsAvailable() {
        return isAvailable;
    }

    /**
     * @param isAvailable the isAvailable to set
     */
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * @return List<TypeOfFuel> return the fuelOptions
     */
    public List<TypeOfFuel> getFuelOptions() {
        return fuelOptions;
    }

    /**
     * @param fuelOptions the fuelOptions to set
     */
    public void setFuelOptions(List<TypeOfFuel> fuelOptions) {
        this.fuelOptions = fuelOptions;
    }

    /**
     * @return String return the soucefuel
     */
    public String getSoucefuel() {
        return soucefuel;
    }

    /**
     * @param soucefuel the soucefuel to set
     */
    public void setSoucefuel(String soucefuel) {
        this.soucefuel = soucefuel;
    }

    /**
     * @return List<Owner> return the owners
     */
    public List<Owner> getOwners() {
        return owners;
    }

    /**
     * @param owners the owners to set
     */
    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    
    /**
     * @param cars the cars to set
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * @return List<Car> return the cars
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * @return String return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return Long return the selectedOwner
     */
    public Long getSelectedOwner() {
        return selectedOwner;
    }

    /**
     * @param selectedOwner the selectedOwner to set
     */
    public void setSelectedOwner(Long selectedOwner) {
        this.selectedOwner = selectedOwner;
    }

   
    /**
     * @return Long return the selectedCar
     */
    public Long getSelectedCar() {
        return selectedCar;
    }

    /**
     * @param selectedCar the selectedCar to set
     */
    public void setSelectedCar(Long selectedCar) {
        this.selectedCar = selectedCar;
    }

    /**
     * @return String return the formState
     */
    public String getFormState() {
        return formState;
    }

    /**
     * @param formState the formState to set
     */
    public void setFormState(String formState) {
        this.formState = formState;
    }
    

    /**
     * @return Long return the sourceCarBrands
     */
    public Long getSourceCarBrands() {
        return sourceCarBrands;
    }

    /**
     * @param sourceCarBrands the sourceCarBrands to set
     */
    public void setSourceCarBrands(Long sourceCarBrands) {
        this.sourceCarBrands = sourceCarBrands;
    }

    /**
     * @return Long return the sourceOwner
     */
    public Long getSourceOwner() {
        return sourceOwner;
    }

    /**
     * @param sourceOwner the sourceOwner to set
     */
    public void setSourceOwner(Long sourceOwner) {
        this.sourceOwner = sourceOwner;
    }
    //#endregion getters and setters
}