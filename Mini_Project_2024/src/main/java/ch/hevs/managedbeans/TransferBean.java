package ch.hevs.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private List<CarBrand> carBrands;
   
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
		carSale = (CarSale) ctx.lookup("java:global/CarSale-0.0.1-SNAPSHOT/CarSaleBean!ch.hevs.carsaleservice.CarSale");
	
    	// get the list of car brands
		carBrands = new ArrayList<CarBrand>();
		carBrands = carSale.getCarBrands();
		
    }

	public String test(){
		return "test";
	}

    /**
     * @return List<CarBrand> return the carBrands
     */
    public List<CarBrand> getCarBrands() {
        return carBrands;
    }

    /**
     * @param carBrands the carBrands to set
     */
    public void setCarBrands(List<CarBrand> carBrands) {
        this.carBrands = carBrands;
    }

}