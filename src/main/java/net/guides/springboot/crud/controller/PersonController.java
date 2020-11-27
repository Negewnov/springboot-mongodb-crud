package net.guides.springboot.crud.controller;

import net.guides.springboot.crud.exception.ResourceNotFoundException;
import net.guides.springboot.crud.model.Person;
import net.guides.springboot.crud.repository.PersonRepository;
import net.guides.springboot.crud.service.SequenceGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	// get all persons
	@GetMapping("/persons")
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

    // create person rest api
	@PostMapping("/persons")
	public Person createPerson(@Valid @RequestBody Person person) {
		person.setId(sequenceGeneratorService.generateSequence(Person.SEQUENCE_NAME));
		return personRepository.save(person);
	}

    // get person by id rest api
	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
		return ResponseEntity.ok().body(person);
	}

    // update person rest api

	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId,
			@Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

		person.setFirstName(personDetails.getFirstName());
		person.setLastName(personDetails.getLastName());
		person.setPhoneNumber(personDetails.getPhoneNumber());
		person.setEmailId(personDetails.getEmailId());

		final Person updatedPerson = personRepository.save(person);
		return ResponseEntity.ok(updatedPerson);
	}

    // delete person rest api
	@DeleteMapping("/persons/{id}")
	public ResponseEntity < Map <String, Boolean>> deletePerson(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

		personRepository.delete(person);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
