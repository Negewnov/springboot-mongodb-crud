package net.guides.springboot.crud.controller;

import net.guides.springboot.crud.exception.ResourceNotFoundException;
import net.guides.springboot.crud.model.Testassets;
import net.guides.springboot.crud.repository.TestassetsRepository;
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
public class TestassetsController {

	@Autowired
	private TestassetsRepository testassetsRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	// get all testassetss
	@GetMapping("/testassetss")
	public List<Testassets> getAllTestassetss() {
		return testassetsRepository.findAll();
	}

    // create testassets rest api
	@PostMapping("/testassetss")
	public Testassets createTestassets(@Valid @RequestBody Testassets testassets) {
		testassets.setId(sequenceGeneratorService.generateSequence(Testassets.SEQUENCE_NAME));
		return testassetsRepository.save(testassets);
	}

    // get testassets by id rest api
	@GetMapping("/testassetss/{id}")
	public ResponseEntity<Testassets> getTestassetsById(@PathVariable(value = "id") Long testassetsId)
			throws ResourceNotFoundException {
		Testassets testassets = testassetsRepository.findById(testassetsId)
				.orElseThrow(() -> new ResourceNotFoundException("Testassets not found for this id :: " + testassetsId));
		return ResponseEntity.ok().body(testassets);
	}

    // update testassets rest api

	@PutMapping("/testassetss/{id}")
	public ResponseEntity<Testassets> updateTestassets(@PathVariable(value = "id") Long testassetsId,
			@Valid @RequestBody Testassets testassetsDetails) throws ResourceNotFoundException {
		Testassets testassets = testassetsRepository.findById(testassetsId)
				.orElseThrow(() -> new ResourceNotFoundException("Testassets not found for this id :: " + testassetsId));

		testassets.setFirstName(testassetsDetails.getFirstName());
		testassets.setLastName(testassetsDetails.getLastName());
		testassets.setPhoneNumber(testassetsDetails.getPhoneNumber());
		testassets.setEmailId(testassetsDetails.getEmailId());

		final Testassets updatedTestassets = testassetsRepository.save(testassets);
		return ResponseEntity.ok(updatedTestassets);
	}

    // delete testassets rest api
	@DeleteMapping("/testassetss/{id}")
	public ResponseEntity < Map <String, Boolean>> deleteTestassets(@PathVariable(value = "id") Long testassetsId)
			throws ResourceNotFoundException {
		Testassets testassets = testassetsRepository.findById(testassetsId)
				.orElseThrow(() -> new ResourceNotFoundException("Testassets not found for this id :: " + testassetsId));

		testassetsRepository.delete(testassets);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
