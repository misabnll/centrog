package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Blogs;
import com.example.demo.entity.Readers;
import com.example.demo.repository.BlogsRepository;
import com.example.demo.repository.ReadersRepository;

@Controller
@RequestMapping("/centrog/blogs")
public class BlogsController {
	private final BlogsRepository repository1;
	private final ReadersRepository repository2;
	
	BlogsController(BlogsRepository repository1, ReadersRepository repository2){
		this.repository1 = repository1;
		this.repository2 = repository2;
	}
	
	@GetMapping("")
	public String lista(Model model) {
		model.addAttribute("Blogs", repository1.findAll());

		return "crud/blogs/lista";
	}

	@GetMapping("/add-update")
	public String add(Model model, Blogs blogs) {
		model.addAttribute("Blogs", blogs);
		model.addAttribute("Readers", repository2.findAll());
		model.addAttribute("Selected", new ArrayList<Long>());

		return "crud/blogs/add-update";
	}

	@PostMapping("/add-update")
	public String save(@ModelAttribute @Valid Blogs blogs, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("Blogs", blogs);

            return "crud/Blogs/add-update";
        }

        repository1.save(blogs);
        return "redirect:/centrog/blogs";
	}

	@GetMapping("/update/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Blogs blogs = repository1.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("" + id));

		model.addAttribute("Blogs", blogs);
		model.addAttribute("Readers", repository2.findAll());
		model.addAttribute("Selected", getListSelectedId(blogs.getReaders()));

		return "crud/blogs/add-update";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		try {
			Blogs blogs = repository1.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("" + id));

			repository1.delete(blogs);
		} catch (Exception e) {
			return "redirect:/centrog/blogs?error";
		}

		return "redirect:/centrog/blogs";
	}
	
	public List<Long> getListSelectedId(Set<Readers> set){
		List<Long> readersList = new ArrayList<Long>();
		for (Readers readers:set) {
			readersList.add(readers.getId());
		}
		return readersList;
	}

}
