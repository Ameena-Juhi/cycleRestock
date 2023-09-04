package com.example.Sudoku.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.Sudoku.entity.Cycle;
import com.example.Sudoku.entity.User;
import com.example.Sudoku.model.CycleService;
import com.example.Sudoku.model.UserService;
import com.example.Sudoku.repository.CycleRepository;
import com.example.Sudoku.repository.UserRepository;

@Controller
public class CycleController {

	@Autowired
	private CycleRepository cycleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CycleService cycleService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/cycle")
	public String ShowAll(Model model) {
		List<Cycle> allcycles = cycleService.listAll();
		model.addAttribute("cycles", allcycles);
		return "CycleShop";
	}
	
	@RequestMapping("/new")
	public String Addnew(Model model) {
		Cycle cycle = new Cycle();
		model.addAttribute("cycle", cycle);
		return "NewCycle";
	}
	
	@PostMapping("/save")
	public String Savecycle(@ModelAttribute("cycle") Cycle cycle ) {
		cycleService.save(cycle);
		return "redirect:/cycle";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView Editcycles(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("EditCycle");
		Cycle cycle = cycleService.get(id);
		mav.addObject("cycle", cycle);
		return mav;
	}
	
	@GetMapping("/borrow/{id}")
	public String borrowCycle(@PathVariable("id") int id) {
		cycleService.borrow(id);
		return "redirect:/cycle";
	}
	
	@GetMapping("/return/{id}")
	public String returnCycle(@PathVariable("id") int id) {
		cycleService.returns(id);
		return "redirect:/cycle";
	}
	
	@GetMapping("/newUser")
	public String newUser(Model model) {
		User user = new User();
		model.addAttribute(user);
		return("Login");
	}
	
	@PostMapping("/login")
	public String Login(@ModelAttribute("user") User user ) {
		if (user.getPassword() != null) {
	        System.out.println(userService.validatePwd(user.getPassword()));
	        return null;
	    }
		else
			return "redirect:/cycle";
	}
}
