package ch.hevs.managedbeans;

import java.io.Serializable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.CarBrand;
import ch.hevs.carsaleservice.CarSale;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
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
	private CarBrand carBrand;
   
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		carSale = (CarSale) ctx.lookup("java:global/CarSale-0.0.1-SNAPSHOT/CarSaleBean!ch.hevs.carsaleservice.CarSale");
    	//get the list of car brands
		carBrand = carSale.getCarBrand("Ford");
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

    /**
     * @param carSale the carSale to set
     */
    public void setCarSale(CarSale carSale) {
        this.carSale = carSale;
    }

    /**
     * @return String return the carBrand
     */
    public String getCarBrand() {
        return carBrand.getName();
    }
}