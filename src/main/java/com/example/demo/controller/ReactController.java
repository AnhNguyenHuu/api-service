
package com.example.demo.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.Approperties;
import com.example.demo.dto.Pagination;
import com.example.demo.dto.ReactDto;
import com.example.demo.dto.ReactJson;
import com.example.demo.model.React;
import com.example.demo.repository.ReactRepository;
import com.example.demo.service.ReactService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api")
public class ReactController {

	@Autowired
	private ReactRepository reactRepository;
	
	@Autowired
	private ReactService reactService;
	
	@Autowired
	private Approperties approperties;
	@GetMapping("/reacts")
	public ResponseEntity<?> getReacts(@RequestParam(required = false) String searchTerm,@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer limit){
		try {
			
			React react =new React();
			List<React> reacts = new ArrayList<React>();
			if(page == null && limit == null) {
				react.setPage(approperties.getDefaultPage());
				react.setSize(approperties.getDefaultSize());
//				reactRepository.findAll().forEach(reacts::add);
			}else if(page != null && limit == null ){
				react.setPage(page-1);
				react.setSize(approperties.getDefaultSize());
//				reactRepository.findByTitleContaining(title).forEach(reacts::add);
			}else if(page == null && limit != null){
				react.setPage(approperties.getDefaultPage());
				react.setSize(limit);
			}else {
				react.setPage(page-1);
				react.setSize(limit);
			}
			
			if(searchTerm != null && !searchTerm.equals("")) {
				react.setTitle(searchTerm);
			}
			
			Page<React> pages = reactService.gets(react);
			
			if(pages == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				System.out.println("pagetotla: " + pages.getNumber() +1);
				ReactJson reactJson = new ReactJson();
				reactJson.setReacts(pages.getContent());
				reactJson.setPagination(new Pagination(pages.getNumber() + 1, pages.getSize(), pages.getTotalElements()));
				
				String jsonData = new ObjectMapper().writeValueAsString(reactJson);
				return new ResponseEntity<>(jsonData,HttpStatus.OK);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/reactedit/{id}")
	public ResponseEntity<?> getReactById(@PathVariable("id") Long id){
		Optional<React> reactData = reactRepository.findById(id);
		if(reactData.isPresent()) {
			return new ResponseEntity<>(reactData.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/reacts")
	public ResponseEntity<?> createReact(@RequestBody React react){
		try {
			
			React reactAdd = reactRepository.save(react);
			return new ResponseEntity<>(reactAdd,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/react")
	public ResponseEntity<?> deleteReact(@RequestParam Long id){
		try {
			System.out.println("delete react: " + id);
			reactRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
