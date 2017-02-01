package finalppro.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finalppro.dao.ReservationRepository;
import finalppro.model.Reservation;
import finalppro.model.User;

@Service
@Transactional
public class ReservationService {

	private final ReservationRepository reservationRepository;
	
	@Autowired
	private UserService userService;
	
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
	
	public List<Reservation> findAllPerCourt(int courtId){
		List<Reservation> reservations = new ArrayList<>();
		for(Reservation reservation : reservationRepository.findAll()){
			if (reservation.getCourt().getId() == courtId){
				reservations.add(reservation);
			}
		}
		return reservations;
	}
	
	public int countFutureReservationForUser(int userId){
		Date now = new Date();
		int count = 0;
		
		for (Reservation reservation : findAllForUser(userId)) {
			if (reservation.getDate_start().after(now)){
				count++;
			}
		}
		
		return count;
	}
	
	public List<Reservation> findAllForUser(int userId){
		
		return (List<Reservation>) userService.findUser(userId).getReservations();
	}
	
	public List<Reservation> findAll(Date dateStart, Date dateEnd, int courtId){
		List<Reservation> wantedReservations = new ArrayList<>();
		for (Reservation reservation : reservationRepository.findAll()) {
			if (reservation.getCourt().getId() == courtId && (
					(reservation.getDate_start().before(dateEnd) && reservation.getDate_start().after(dateStart)) ||
					(reservation.getDate_end().after(dateStart) && reservation.getDate_end().before(dateEnd)) ||
					(reservation.getDate_start().after(dateStart) && reservation.getDate_end().before(dateEnd)) ||
					(reservation.getDate_start().before(dateStart) && reservation.getDate_end().after(dateEnd))
					)
					){
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
