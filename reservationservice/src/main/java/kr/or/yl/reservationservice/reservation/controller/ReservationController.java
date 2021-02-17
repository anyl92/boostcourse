package kr.or.yl.reservationservice.reservation.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.yl.reservationservice.reservation.domain.Reservation;
import kr.or.yl.reservationservice.reservation.dto.ReservationInfoReadResponse;
import kr.or.yl.reservationservice.reservation.dto.ReservationReadRequest;
import kr.or.yl.reservationservice.reservation.service.ReservationService;


@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	private static final String FILE_PATH = "C:/dev"; 
	
	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public ResponseEntity<ReservationInfoReadResponse> getReservations(String email) {
		List<Reservation> reservations = reservationService.getReservations(email);
		
		return ResponseEntity
				.ok(new ReservationInfoReadResponse(reservations));
	}

	@PostMapping
	public ResponseEntity<String> addReservation(@RequestBody ReservationReadRequest reservationReadRequest) {
		reservationService.addReservation(reservationReadRequest);
		
		return ResponseEntity
				.ok("success");
	}

	@PutMapping("/{reservationInfoId}")
	public ResponseEntity<Object> cancleReservation(@PathVariable int reservationInfoId) {
		boolean isCanceled = reservationService.cancleReservation(reservationInfoId);
		
		if (isCanceled == false) {
			return ResponseEntity
					.status(500).build();
		}
		return ResponseEntity
				.ok("success");
	}

	@PostMapping("/{reservationInfoId}/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		String randomDate = LocalDateTime.now().toString();
		String saveFileName = file.getOriginalFilename() + randomDate;
		
        try ( FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH + saveFileName);
        	  InputStream inputStream = file.getInputStream()) {
        	
    	    int readCount = 0;
    	    byte[] buffer = new byte[1024];
    	    while((readCount = inputStream.read(buffer)) != -1){
    	    	fileOutputStream.write(buffer, 0, readCount);
    	    }
    	    return saveFileName;
    	    
	    } catch (Exception exception) {
	        throw new RuntimeException("file Save Error", exception);
	    }
	}

}
