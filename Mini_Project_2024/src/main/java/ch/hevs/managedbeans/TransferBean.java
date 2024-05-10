package ch.hevs.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.bankservice.Bank;
import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Buyer;
import ch.hevs.businessobject.CarBrand;
import ch.hevs.carsaleservice.CarSale;
import ch.hevs.carsaleservice.CarSaleBean;
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
public class TransferBean  implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarSale sale;
	private List<CarBrand> carBrands;
   
    
    @PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
		sale = (CarSale) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-0.0.1-SNAPSHOT/SaleBean!ch.hevs.carsaleservice.Sale");
	
    	// get the list of car brands
		carBrands = new ArrayList<CarBrand>();

		
    }

    /**
     * @return Sale return the sale
     */
    public CarSale getSale() {
        return sale;
    }

    /**
     * @param sale the sale to set
     */
    public void setSale(CarSale sale) {
        this.sale = sale;
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