package finalppro.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import finalppro.dao.AddressRepository;
import finalppro.dao.CourtRepository;
import finalppro.model.Address;
import finalppro.model.Court;

@Service
@Transactional
public class AddressService {

	private final AddressRepository addressRepository;
	
	public AddressService(AddressRepository addressRepository){
		this.addressRepository = addressRepository;
	}
	
	public List<Address> findAll(){
		List<Address> addreses = new ArrayList<>();
		for(Address address : addressRepository.findAll()){
			addreses.add(address);
		}
		return addreses;
	}
	
	
	public Address findCourt(int id){
		return addressRepository.findOne(id);
	}
	
	public void save(Address address){
		addressRepository.save(address);
	}
	
	public void delete(int id){
		addressRepository.delete(id);
	}
}
