package kr.or.yl.reservationservice.main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.or.yl.reservationservice.reservation.domain.Reservation;
import kr.or.yl.reservationservice.reservation.service.ReservationService;

@Controller
public class PageController {
	
	@Autowired
	private ReservationService reservationService;
	
	@GetMapping(value = "/")
	public String main() {
		return "mainpage";
	}

	@GetMapping("detail/{id}")
	public String detail(@PathVariable(name="id") int id, Model model) {
		model.addAttribute("displayInfoId", id);
		return "detail";
	}

	@GetMapping("review/{id}")
	public String review(@PathVariable(name="id") int id, Model model) {
		model.addAttribute("displayInfoId", id);
		return "review";
	}
	
	@GetMapping("reserve/{id}")
	public String reserve(@PathVariable(name="id") int id, Model model) {
		model.addAttribute("displayInfoId", id);
		return "reserve";
	}
	
	@GetMapping("bookinglogin")
	public String bookinglogin() {
		return "bookinglogin";
	}
	
	@GetMapping("myreservation")
	public void myreservation(String email, HttpSession session, Model model) {
		List<Reservation> reservations = reservationService.getReservations(email);

		if (reservations.isEmpty()) {
			throw new EmptyResultDataAccessException("예약 정보가 없습니다.", 1);
		}
		session.setAttribute("email", email);
	}
	
	@GetMapping("reviewWrite/{id}")
	public String reviewWrite(@PathVariable(name="id") int id, Model model) {
		model.addAttribute("displayInfoId", id);
		return "reviewWrite";
	}
	
}
