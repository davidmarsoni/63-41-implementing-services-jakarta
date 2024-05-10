package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Buyer;
import jakarta.ejb.Stateless;

@Stateless
public class BankBean implements Bank {
	@Override
	public Account getAccount(String accountDescription, String lastnameOwner) {
		return null;
	}

	@Override
	public List<Account> getAccountListFromClientLastname(String lastname) {
		return null;
	}

	@Override
	public void transfer(Account compteSrc, Account compteDest, int montant) throws Exception {

	}

	@Override
	public List<Buyer> getClients() {
		return null;
	}

	@Override
	public Buyer getClient(long clientid) {
		return null;
	}
	/*
	@PersistenceContext(unitName = "bankPU", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

	public Account getAccount(String accountDescription, String lastnameOwner) {
		
		
		Query query = em.createQuery("FROM Account a WHERE a.description=:description AND a.owner.lastname=:lastname");
		query.setParameter("description", accountDescription);
		query.setParameter("lastname", lastnameOwner);
		
		Account account = (Account) query.getSingleResult();
		
		return account;
	}
	
	public List<Account> getAccountListFromClientLastname(String lastname) {
		return (List<Account>) em.createQuery("SELECT c.accounts FROM Buyer c where c.lastname=:lastname").setParameter("lastname", lastname).getResultList();
	}

	public void transfer(Account srcAccount, Account destAccount, int amount) {
		
		System.out.println("ID source account called from transfer(): " + srcAccount.getId());
		System.out.println("ID destination account called from transfer(): " + destAccount.getId());
		
		Account srcRealAccount = em.merge(srcAccount);
		Account destRealAccount = em.merge(destAccount);
		srcRealAccount.debit(amount);
		destRealAccount.credit(amount);
	}

	public List<Buyer> getClients() {
		return em.createQuery("FROM Buyer").getResultList();
	}
	
	public Buyer getClient(long clientid) {
		return (Buyer) em.createQuery("FROM Buyer c where c.id=:id").setParameter("id", clientid).getSingleResult();
	}*/
}
