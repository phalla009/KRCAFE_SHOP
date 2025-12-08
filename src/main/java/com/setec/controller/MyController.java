package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.setec.entities.Booked;
import com.setec.repo.BookedRepo;
import com.setec.services.MyTelegramBot;

@Controller
public class MyController {

//	http://localhost:8080/
	@GetMapping({ "/", "/home" })
	public String home(Model mod) {
		Booked booked = new Booked(1, "Heang Phalla", "0964775515", "hphalla3@gmail.com", "24/11/2025", "3:19 PM", 5);

		mod.addAttribute("booked", booked);
		return "index";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/service")
	public String service() {
		return "service";
	}

	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}

	@GetMapping("/reservation")
	public String reservation(Model mod) {
		Booked booked = new Booked(1, "Heang Phalla", "0964775515", "hphalla3@gmail.com", "24/11/2025", "3:19 PM", 5);

		mod.addAttribute("booked", booked);
		return "reservation";
	}

	@GetMapping("/testimonial")
	public String testimonial() {
		return "testimonial";
	}


	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	@Autowired
	private BookedRepo bookedRepo;
	@Autowired
	private MyTelegramBot bot;

	@PostMapping("/success")
	public String success(@ModelAttribute Booked booked) {
		bookedRepo.save(booked);
		String message = String.format(
			    "📅  Booking Confirmation\n\n" +
			    "🆔  Booking ID: %d\n" +
			    "👤  Name: %s\n" +
			    "📞  Phone: %s\n" +
			    "✉️  Email: %s\n" +
			    "📅  Date: %s\n" +
			    "⏰  Time: %s\n" +
			    "👥  Number of People: %d",
			    booked.getId(),
			    booked.getName(),
			    booked.getPhoneNumber(),
			    booked.getEmail(),
			    booked.getDate(),
			    booked.getTime(),
			    booked.getPerson()
			);

			bot.sendMessage(message);
	
		return "success";
	}
}
