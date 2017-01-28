package finalppro.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import finalppro.dao.ReservationRepository;
import finalppro.model.Reservation;

@Service
@Transactional
public class ReservationService {

	private final ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository){
		this.reservationRepository = reservationRepository;
	}
	
	public List<Reservation> findAll(){
		List<Reservation> reservations = new ArrayList<>();
		for(Reservation reservation : reservationRepository.findAll()){
			reservations.add(reservation);
		}
		return reservations;
	}
	
	public List<Reservation> findAll(Date dateStart, Date dateEnd, int courtId){
		List<Reservation> wantedReservations = new ArrayList<>();
		for (Reservation reservation : reservationRepository.findAll()) {
			if (reservation.getCourt().getId() == courtId && reservation.getDate_start().after(dateStart) && reservation.getDate_end().before(dateEnd)){
				wantedReservations.add(reservation);				
			} else {				
			}
		}
				
		return wantedReservations;
	}
	
	public Reservation findReservation(int id){
		return reservationRepository.findOne(id);
	}
	
	public void save(Reservation reservation){
		reservationRepository.save(reservation);
	}
	
	public void delete(int id){
		reservationRepository.delete(id);
	}
}
