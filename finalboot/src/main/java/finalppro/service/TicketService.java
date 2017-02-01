package finalppro.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finalppro.dao.TicketRepository;
import finalppro.dao.TicketRepository;
import finalppro.model.Ticket;
import finalppro.model.TicketType;
import finalppro.model.User;
import finalppro.model.UserType;

@Service
@Transactional
public class TicketService {

	private final TicketRepository ticketRepository;
	
	@Autowired
	UserService userService;
	
	public TicketService(TicketRepository ticketRepository){
		this.ticketRepository = ticketRepository;
	}
	
	public List<Ticket> findAll(){
		List<Ticket> tickets = new ArrayList<>();
		for(Ticket ticket : ticketRepository.findAll()){
			tickets.add(ticket);
		}
		return tickets;
	}
	
	public int countUsage(int ticketId){
		int count = 0;
		
		for (User user : userService.findAll()) {
			if (user.getTicket().getId() == ticketId){
				count++;
			}
		}
		
		return count;
	}
	
	public Ticket findTicket(int id){
		return ticketRepository.findOne(id);
	}
	
	public Ticket findTicket(String ticketName){
		Ticket r = null;
		for (Ticket ticket : ticketRepository.findAll()) {
			if (TicketType.values()[ticket.getTicketType().ordinal()].toString().equals(ticketName)){
				r = ticket;
			}
		}
		
		return r;
	}
	
	public void save(Ticket ticket){
		ticketRepository.save(ticket);
	}
	
	public void delete(int id){
		ticketRepository.delete(id);
	}
}
