package finalppro.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import finalppro.dao.CourtRepository;
import finalppro.model.Court;

@Service
@Transactional
public class CourtService {

	private final CourtRepository courtRepository;
	
	public CourtService(CourtRepository courtRepository){
		this.courtRepository = courtRepository;
	}
	
	public List<Court> findAll(){
		List<Court> courts = new ArrayList<>();
		for(Court court : courtRepository.findAll()){
			courts.add(court);
		}
		return courts;
	}
	
	public List<Court> findAllActive(){
		List<Court> courts = new ArrayList<>();
		for(Court court : courtRepository.findAll()){
			if (court.isActive()){
				courts.add(court);
			} 
		}
		return courts;
	}
	
	public Court findCourt(int id){
		return courtRepository.findOne(id);
	}
	
	public void save(Court court){
		courtRepository.save(court);
	}
	
	public void delete(int id){
		courtRepository.delete(id);
	}
}
