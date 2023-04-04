package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.BlogsRepository;

@Controller
@RequestMapping("/centrog/readers-por-blog")
public class ReadersBlogController {
	private final BlogsRepository repository1;
	
	ReadersBlogController(BlogsRepository repository1){
		this.repository1 = repository1;
	}
	
	@GetMapping("")
	public String lista(Model model) {
		model.addAttribute("Blogs", repository1.findAll());
//		System.out.println(repository1.getReaderBlogs().get(0).getReaders());
//		
//		List<String> readersList = new ArrayList<String>();
//		
//		for (Readers readers:repository1.getReaderBlogs().get(2).getReaders()) {
//			readersList.add(readers.getName());
//	    }
//		
//		System.out.println(readersList);
		
		return "crud/readers-por-blog/lista";
	}
}
