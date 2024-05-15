package ch.hevs.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

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
public class TransferBean implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarSale carSale;
    // list of car brands and the selected car brand
    private List<CarBrand> carBrands;
    private String sourceCarBrands;

    // list of type of fuel and the selected type of fuel
    private List<TypeOfFuel> fuelOptions;
    private String soucefuel;

    // list of owners and the selected owner
    private List<Owner> owners;
    private String sourceOwner;

    //property for the car 
    private String model;
    private int year_of_construction;
    private int kilometers;
    private String color;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;
    

    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		carSale = (CarSale) ctx.lookup("java:global/CarSale-0.0.1-SNAPSHOT/CarSaleBean!ch.hevs.carsaleservice.CarSale");
    	//get the list of car brands
        carBrands = carSale.getCarBrands();
        owners = carSale.getOwners();
        //get all the choices of the enum TypeOfFuel
        fuelOptions = List.of(TypeOfFuel.values());
    }


    public boolean addNewCar() {
    	return carSale.addCar(sourceCarBrands, model, year_of_construction, kilometers, soucefuel, color, description, price, isAvailable, sourceOwner);
    }

	public String test(){
		return carSale.test();
	}

    /**
     * @return CarSale return the carSale
     */
    public CarSale getCarSale() {
        return carSale;
    }

    public void updateSourceCarBrands(ValueChangeEvent event) {
    	//TODO
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
     * @return String return the sourceCarBrands
     */
    public String getSourceCarBrands() {
        return sourceCarBrands;
    }

    /**
     * @param sourceCarBrands the sourceCarBrands to set
     */
    public void setSourceCarBrands(String sourceCarBrands) {
        this.sourceCarBrands = sourceCarBrands;
    }

    /**
     * @return String return the sourceOwner
     */
    public String getSourceOwner() {
        return sourceOwner;
    }

    /**
     * @param sourceOwner the sourceOwner to set
     */
    public void setSourceOwner(String sourceOwner) {
        this.sourceOwner = sourceOwner;
    }

}