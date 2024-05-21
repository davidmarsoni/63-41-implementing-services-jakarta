package ch.hevs.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Buyer;
import ch.hevs.businessobject.Car;
import ch.hevs.businessobject.CarBrand;
import ch.hevs.businessobject.Owner;
import ch.hevs.businessobject.Sale;
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

    // list of car brands and the selected car brand
    private List<CarBrand> carBrands;
    private Long sourceCarBrands;

    // list of type of fuel and the selected type of fuel
    private List<TypeOfFuel> fuelOptions;
    private String soucefuel;

    // list of owners and the selected owner
    private List<Owner> owners;
    private Long selectedOwner;

    // property for the car
    private String model;
    private int year_of_construction;
    private int kilometers;
    private String color;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;
    
    private String formState;

    // === car buy ===
    private List<Buyer> buyers;
    private Long selectedBuyer;

    private Long selectedCarBrand;
    //=== buyer dashnoard ===

    //=== owner dashboard ===
    private List<Sale> sales;

    // === info ===
    private String info;

    @PostConstruct
    public void initialize() throws NamingException {

        // use JNDI to inject reference to bank EJB
        InitialContext ctx = new InitialContext();
        carSale = (CarSale) ctx.lookup("java:global/CarSale-0.0.1-SNAPSHOT/CarSaleBean!ch.hevs.carsaleservice.CarSale");
        // get the list of car brands
        carBrands = carSale.getCarBrands();
        owners = carSale.getOwners();
        buyers = carSale.getBuyers();

        // get all the choices of the enum TypeOfFuel
        fuelOptions = List.of(TypeOfFuel.values());
    }

    // === navigation ===
    public String navigateToOwnerDashboard() {
        clearInfo();
        return "ownerDashboard";
    }
    
    public String navigateToBuyerDashboard() {
        clearInfo();
        return "buyerDashboard";
    }

    public String navigateToManageCars(){
        clearInfo();
        return "manageCars";
    }

    public String navigateToCarsBuy(){
        clearInfo();
        return "carsBuy";
    }

    // === preloading ===
    public void manageCarsPreload() {
        if(owners.isEmpty()) {
            setInfo("No owners found please add an owner first");
            return;
        }
        if(getFormState() == null){
            setFormState("Add");
        }

        if(selectedOwner == null){
            selectedOwner = owners.get(0).getId();
        }
        updateCarList();
    }
 
    public void carsBuyPreload() {
        if(buyers.isEmpty()) {
            setInfo("No buyers found please add a buyer first");
            return;
        }

        if(selectedBuyer == null){
            selectedBuyer = buyers.get(0).getId();
        }
        filterCarList();
    }

    public void ownerDashboardPreload() {
        if(owners.isEmpty()) {
            setInfo("No owners found please add an owner first");
            return;
        }
        if(getFormState() == null){
            setFormState("Add");
        }

        if(selectedOwner == null){
            selectedOwner = owners.get(0).getId();
        }
        updateSaleListByOwner();
    }

    public void buyerDashboardPreload() {
        if(buyers.isEmpty()) {
            setInfo("No buyers found please add a buyer first");
            return;
        }
        if(selectedBuyer == null){
            selectedBuyer = buyers.get(0).getId();
        }

        sales = carSale.getSalesByBuyer(selectedBuyer);
    }

    // === info ===
    public void clearInfo() {
        setInfo(null);
    }

    // === user security ===
    public String currentUser() {
        return carSale.getCurrentUser();
    }

    public boolean isUserAdmin() {
        return carSale.isUserAdmin();
    }

    public boolean isUserOwner() {
        return carSale.isUserOwner();
    }

    public boolean isUserBuyer() {
        return carSale.isUserBuyer();
    }

    public String currentUserRole() {
        return carSale.getCurrentUserRole();
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

    public void removeCar(Car car) {
        String message = carSale.removeCar(car.getId());
        updateCarList();
        setInfo(message);
    }

    public void updateSourceOwner(ValueChangeEvent event) {
        // get the new value from the event
        selectedOwner = Long.parseLong(event.getNewValue().toString());
        //update the list of cars owned by the owner
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

    // === car buy ===
    public void updateSourceBuyer(ValueChangeEvent event) {
        // get the new value from the event
        selectedBuyer = Long.parseLong(event.getNewValue().toString());
        // set the info message
        setInfo("Buyer selected: " + carSale.getBuyer(getSelectedBuyer()).getFullName());
    }

    public void updateSourceCarBrand(ValueChangeEvent event) {
        // get the new value from the event
        selectedCarBrand = Long.parseLong(event.getNewValue().toString());
        //equals -1000 means no car brand selected
        if(selectedCarBrand == -1000L) {
            selectedCarBrand = null;
        }
        // set the info message
        setInfo("Car brand selected: ");
        if(selectedCarBrand != null) {
            setInfo(getInfo() + carSale.getCarBrand(selectedCarBrand).getName());
        } else {
            setInfo(getInfo() + "All car brands");
        }
    }

    public void filterCarList() {
        cars = carSale.getFilterdCars(selectedCarBrand,null,0,0,0,0,null,null,null,null);
    }
    
    public void buyCar(Car car) {
        String message = carSale.buyCar(car.getId(), selectedBuyer,price);
        filterCarList();
        setInfo(message);
    }
    // === buyer dashboard ===
    public void updateBuyer(ValueChangeEvent event) {
        // get the new value from the event
        selectedBuyer = Long.parseLong(event.getNewValue().toString());

        updateSaleListByBuyer();
        // set the info message
        setInfo("Buyer selected: " + carSale.getBuyer(getSelectedBuyer()).getFullName());
    }

    public void updateSaleListByBuyer(){
        sales = carSale.getSalesByBuyer(selectedBuyer);
    }

    // === owner dashboard ===

    public void acceptSale(Long saleId) {
        String message = carSale.acceptSale(saleId);
        updateSaleListByOwner();
        setInfo(message);
    }

    public void refuseSale(Long saleId) {
        String message = carSale.refuseSale(saleId);
        updateSaleListByOwner();
        setInfo(message);
    }

    public void updateOwner(ValueChangeEvent event) {
        // get the new value from the event
        selectedOwner = Long.parseLong(event.getNewValue().toString());

        updateSaleListByOwner();
        // set the info message
        setInfo("Owner selected: " + carSale.getOwner(getSelectedOwner()).getFullName());
    }

    public void updateSaleListByOwner(){
        sales = carSale.getSalesByOwner(selectedOwner);
    }

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
     * @return List<Buyer> return the buyers
     */
    public List<Buyer> getBuyers() {
        return buyers;
    }

    /**
     * @param buyers the buyers to set
     */
    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    /**
     * @return Long return the selectedBuyer
     */
    public Long getSelectedBuyer() {
        return selectedBuyer;
    }

    /**
     * @param selectedBuyer the selectedBuyer to set
     */
    public void setSelectedBuyer(Long selectedBuyer) {
        this.selectedBuyer = selectedBuyer;
    }

   

    /**
     * @return Long return the selectedCarBrand
     */
    public Long getSelectedCarBrand() {
        return selectedCarBrand;
    }

    /**
     * @param selectedCarBrand the selectedCarBrand to set
     */
    public void setSelectedCarBrand(Long selectedCarBrand) {
        this.selectedCarBrand = selectedCarBrand;
    }

    /**
     * @param sales the sales to set
     */
    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    /**
     * @return List<Sale> return the sales
     */
    public List<Sale> getSales() {
        return sales;
    }

    //#endregion getters and setters
}