package ch.hevs.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import ch.hevs.businessobject.*;
import jakarta.persistence.*;

import org.junit.Test;
import junit.framework.TestCase;


public class PopulateDB extends TestCase {

	@Test
	public void test() throws SQLException, ClassNotFoundException {

		EntityTransaction tx = null;
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("carSalePU_unitTest");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			// populate the database with car brands
			List<CarBrand> carBrands = populateCarBrands(em);
			// populate the database with some data

			Owner o1 = new Owner(
					"JeanDupont",
					"Jean",
					"Dupont",
					"Rue de la Gare 12",
					"079 123 45 67",
					"jeandupont@gmail.com",
					LocalDate.parse("1990-01-01"),
					"CH4989144487571917565" //fake IBAN that is (provided by http://randomiban.com/?country=Switzerland)
			);
			Owner o2 = new Owner(
					"MarieDurand",
					"Marie",
					"Durand",
					"Place de la commune 1",
					"079 123 45 67",
					"marie@google.com",
					LocalDate.parse("1990-01-01"),
					"CH4989144487571917565" //fake IBAN that is (provided by http://randomiban.com/?country=Switzerland)
			);
			Buyer b1 = new Buyer(
				"PaulDurand",
				"Paul",
				"Durand",
				"Rue de la Gare 12",
				"079 123 45 67",
				"durandalpaulie@yahoo-mail.com",
				LocalDate.parse("1990-01-01")
			);

			Buyer b2 = new Buyer(
				"JacquesDupont",
				"Jacques",
				"Dupont",
				"Place de la commune 1",
				"079 123 45 67",
				"jack@gmail.com",
				LocalDate.parse("1990-01-01")
			);

			Car car = new Car(
					carBrands.get(0),
					"Ford Fiesta 2020",
					2022,
					100000,
					TypeOfFuel.HYBRID,
					BigDecimal.valueOf(20000),
					true,
					"Blue",
					"Ford Fiesta 2020 in good condition"
			);

			
			em.persist(o2);
			em.persist(b1);
			em.persist(b2);

			em.persist(car);
			o1.addCar(car);
			em.persist(o1);

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Populate the database with car brands.
	 */
	public List<CarBrand> populateCarBrands(EntityManager em) {

		CarBrand ford = new CarBrand("Ford", "USA", "https://www.ford.com", "Ford is an American multinational automaker, founded by Henry Ford in 1903.");
		CarBrand toyota = new CarBrand("Toyota", "Japan", "https://www.toyota.com", "Toyota is a Japanese multinational automotive manufacturer, founded in 1937.");
		CarBrand vw = new CarBrand("Volkswagen", "Germany", "https://www.volkswagen.com", "Volkswagen is a German automaker, known for the iconic Beetle, founded in 1937.");
		CarBrand renault = new CarBrand("Renault", "France", "https://www.renault.com", "Groupe Renault is a French multinational automobile manufacturer established in 1899.");
		CarBrand mercedes = new CarBrand("Mercedes-Benz", "Germany", "https://www.mercedes-benz.com", "Mercedes-Benz is a German global automobile marque, a division of Daimler AG.");
		CarBrand bmw = new CarBrand("BMW", "Germany", "https://www.bmw.com", "BMW is a German multinational corporation which produces luxury vehicles, founded in 1916.");
		CarBrand audi = new CarBrand("Audi", "Germany", "https://www.audi.com", "Audi AG is a German automobile manufacturer, a subsidiary of the Volkswagen Group.");
		CarBrand peugot = new CarBrand("Peugeot", "France", "https://www.peugeot.com", "Peugeot is a French automotive manufacturer, part of Groupe PSA.");
		CarBrand citroen = new CarBrand("Citroën", "France", "https://www.citroen.com", "Citroën is a French automobile manufacturer, part of Groupe PSA since 1976.");
		CarBrand fiat = new CarBrand("Fiat", "Italy", "https://www.fiat.com", "Fiat Automobiles S.p.A. is an Italian automobile manufacturer, a subsidiary of Stellantis.");
		CarBrand alfaromeo = new CarBrand("Alfa Romeo", "Italy", "https://www.alfaromeo.com", "Alfa Romeo is an Italian premium car manufacturer, a subsidiary of Stellantis.");
		CarBrand lancia = new CarBrand("Lancia", "Italy", "https://www.lancia.com", "Lancia Automobiles S.p.A. was an Italian automobile manufacturer founded in 1906.");
		CarBrand maserati = new CarBrand("Maserati", "Italy", "https://www.maserati.com", "Maserati is an Italian luxury vehicle manufacturer established in 1914.");
		CarBrand ferrari = new CarBrand("Ferrari", "Italy", "https://www.ferrari.com", "Ferrari S.p.A. is an Italian luxury sports car manufacturer, founded in 1939.");
		CarBrand prosche = new CarBrand("Porsche", "Germany", "https://www.porsche.com", "Porsche AG is a German automobile manufacturer specializing in high-performance sports cars.");
		CarBrand jaguar = new CarBrand("Jaguar", "United Kingdom", "https://www.jaguar.com", "Jaguar is the luxury vehicle brand of Jaguar Land Rover, a British multinational car manufacturer.");
		CarBrand landrover = new CarBrand("Land Rover", "United Kingdom", "https://www.landrover.com", "Land Rover is a British brand of predominantly four-wheel drive vehicles, owned by Jaguar Land Rover.");
		CarBrand volvo = new CarBrand("Volvo", "Sweden", "https://www.volvocars.com", "Volvo Cars is a Swedish luxury automobile marque, headquartered in Gothenburg.");
		CarBrand saab = new CarBrand("Saab", "Sweden", "https://www.saab.com", "Saab Automobile AB was a manufacturer that was founded in Sweden in 1945.");
		CarBrand koenigsegg = new CarBrand("Koenigsegg", "Sweden", "https://www.koenigsegg.com", "Koenigsegg is a Swedish manufacturer of high-performance sports cars.");
		CarBrand bugatii = new CarBrand("Bugatti", "France", "https://www.bugatti.com", "Bugatti was a French car manufacturer of high-performance automobiles, founded in 1909.");
		CarBrand lamborghini = new CarBrand("Lamborghini", "Italy", "https://www.lamborghini.com", "Lamborghini is an Italian brand of luxury sports cars and SUVs, owned by Volkswagen Group.");
		CarBrand mazda = new CarBrand("Mazda", "Japan", "https://www.mazda.com", "Mazda is a Japanese multinational automaker based in Hiroshima Prefecture, Japan.");
		CarBrand subaru = new CarBrand("Subaru", "Japan", "https://www.subaru.com", "Subaru Corporation is a Japanese multinational corporation, known for its line of Subaru automobiles.");
		CarBrand nissan = new CarBrand("Nissan", "Japan", "https://www.nissan.com", "Nissan is a Japanese multinational automobile manufacturer headquartered in Yokohama, Japan.");
		CarBrand honda = new CarBrand("Honda", "Japan", "https://www.honda.com", "Honda is a Japanese multinational conglomerate manufacturer of automobiles, motorcycles, and power equipment.");
		CarBrand suzuki = new CarBrand("Suzuki", "Japan", "https://www.suzuki.com", "Suzuki is a Japanese multinational corporation headquartered in Hamamatsu, manufactures automobiles and motorcycles.");
		CarBrand mitsubishi = new CarBrand("Mitsubishi", "Japan", "https://www.mitsubishi.com", "The Mitsubishi Group is a group of autonomous Japanese multinational companies.");
		CarBrand hyundai = new CarBrand("Hyundai", "South Korea", "https://www.hyundai.com", "Hyundai is a South Korean multinational automotive manufacturer headquartered in Seoul.");
		CarBrand kia = new CarBrand("Kia", "South Korea", "https://www.kia.com", "Kia is a South Korean multinational automotive manufacturer headquartered in Seoul.");
		CarBrand chevrolet = new CarBrand("Chevrolet", "USA", "https://www.chevrolet.com", "Chevrolet is an American automobile division of the American manufacturer General Motors.");
		CarBrand cadillac = new CarBrand("Cadillac", "USA", "https://www.cadillac.com", "Cadillac is a division of the American automobile manufacturer General Motors.");
		CarBrand buick = new CarBrand("Buick", "USA", "https://www.buick.com", "Buick is a division of the American automobile manufacturer General Motors.");
		CarBrand gmc = new CarBrand("GMC", "USA", "https://www.gmc.com", "GMC is a division of the American automobile manufacturer General Motors.");
		CarBrand jeep = new CarBrand("Jeep", "USA", "https://www.jeep.com", "Jeep is a brand of American automobile and a division of Stellantis.");
		CarBrand dodge = new CarBrand("Dodge", "USA", "https://www.dodge.com", "Dodge is an American brand of automobiles and a division of Stellantis.");
		CarBrand chrysler = new CarBrand("Chrysler", "USA", "https://www.chrysler.com", "Chrysler is one of the \"Big Three\" automobile manufacturers in the United States.");
		CarBrand ram = new CarBrand("Ram", "USA", "https://www.ram.com", "Ram Trucks is an American brand of commercial vehicles, a division of FCA US LLC.");

		// add all to a list
		List<CarBrand> carBrands = List.of(ford, toyota, vw, renault, mercedes, bmw, audi, peugot, citroen, fiat, alfaromeo, lancia, maserati, ferrari, prosche, jaguar, landrover, volvo, saab, koenigsegg, bugatii, lamborghini, mazda, subaru, nissan, honda, suzuki, mitsubishi, hyundai, kia, chevrolet, cadillac, buick, gmc, jeep, dodge, chrysler, ram);

		// add all car brands to the database
		for (CarBrand brand: carBrands) {
			em.persist(brand);
		}

		// add all to a list
		return carBrands;
	}
}
