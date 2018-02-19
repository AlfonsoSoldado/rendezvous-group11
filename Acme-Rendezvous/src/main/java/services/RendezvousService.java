package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import domain.Rendezvous;

@Service
@Transactional
public class RendezvousService {

	// Managed repository

	@Autowired
	private RendezvousRepository rendezvousRepository;

	// Suporting services

	// Constructor

	public RendezvousService() {
		super();
	}
	
	// Simple CRUD methods
	
		public Rendezvous create(){
			Rendezvous res = new Rendezvous();
			
			return res;
		}
		
		public Collection<Rendezvous> findAll(){
			Collection<Rendezvous> res;
			res = this.rendezvousRepository.findAll();
			Assert.notNull(res);
			return res;
		}
		
		public Rendezvous findOne(int rendezvousId){
			Assert.isTrue(rendezvousId != 0);
			Rendezvous res;
			res = this.rendezvousRepository.findOne(rendezvousId);
			Assert.notNull(res);
			return res;
		}
		
		public Rendezvous save(Rendezvous rendezvous) {
			Assert.notNull(rendezvous);
			Rendezvous res;
			res = this.rendezvousRepository.save(rendezvous);
			return res;
		}
		
		public void delete(Rendezvous rendezvous) {
			Assert.notNull(rendezvous);
			Assert.isTrue(rendezvous.getId() != 0);
			Assert.isTrue(this.rendezvousRepository.exists(rendezvous.getId()));
			this.rendezvousRepository.delete(rendezvous);
		}
}
