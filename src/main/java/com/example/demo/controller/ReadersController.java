package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Readers;
import com.example.demo.repository.ReadersRepository;

@Controller
@RequestMapping("/centrog/readers")
public class ReadersController {
	private final ReadersRepository repository1;
	
	ReadersController(ReadersRepository repository1){
		this.repository1 = repository1;
	}

	@GetMapping("")
	public String lista(Model model) {
		model.addAttribute("Readers", repository1.findAll());

		return "crud/readers/lista";
	}

	@GetMapping("/add-update")
	public String add(Model model, Readers readers) {
		model.addAttribute("Readers", readers);

		return "crud/readers/add-update";
	}

	@PostMapping("/add-update")
	public String save(@ModelAttribute @Valid Readers readers, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("Readers", readers);

            return "crud/readers/add-update";
        }

        repository1.save(readers);
        return "redirect:/centrog/readers";
	}

	@GetMapping("/update/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Readers readers = repository1.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("" + id));

		model.addAttribute("Readers", readers);

		return "crud/readers/add-update";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		try {
			Readers readers = repository1.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("" + id));
			
			repository1.delete(readers);
		} catch (Exception e) {
			return "redirect:/centrog/readers?error";
		}

		return "redirect:/centrog/readers";
	}
}
