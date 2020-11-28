package net.guides.springboot.crud.controller;

import net.guides.springboot.crud.exception.ResourceNotFoundException;
import net.guides.springboot.crud.model.Testasset;
import net.guides.springboot.crud.repository.TestassetRepository;
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
public class TestassetController {

	@Autowired
	private TestassetRepository testassetRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	// get all testasset
	@GetMapping("/testassets")
	public List<Testasset> getAllTestasset() {
		return testassetRepository.findAll();
	}

    // create testassets rest api
	@PostMapping("/testassets")
	public Testasset createTestasset(@Valid @RequestBody Testasset testasset) {
		testasset.setId(sequenceGeneratorService.generateSequence(Testasset.SEQUENCE_NAME));
		return testassetRepository.save(testasset);
	}

    // get testasset by id rest api
	@GetMapping("/testassets/{id}")
	public ResponseEntity<Testasset> getTestassetById(@PathVariable(value = "id") Long testassetId)
			throws ResourceNotFoundException {
		Testasset testasset = testassetRepository.findById(testassetId)
				.orElseThrow(() -> new ResourceNotFoundException("Testasset not found for this id :: " + testassetId));
		return ResponseEntity.ok().body(testasset);
	}

    // update testasset rest api

	@PutMapping("/testassets/{id}")
	public ResponseEntity<Testasset> updateTestasset(@PathVariable(value = "id") Long testassetId,
			@Valid @RequestBody Testasset testassetDetails) throws ResourceNotFoundException {
		Testasset testasset = testassetRepository.findById(testassetId)
				.orElseThrow(() -> new ResourceNotFoundException("Testasset not found for this id :: " + testassetId));

		testasset.setFirstName(testassetDetails.getFirstName());
		testasset.setLastName(testassetDetails.getLastName());
		testasset.setPhoneNumber(testassetDetails.getPhoneNumber());
		testasset.setEmailId(testassetDetails.getEmailId());

		final Testasset updatedTestasset = testassetRepository.save(testasset);
		return ResponseEntity.ok(updatedTestasset);
	}

    // delete testasset rest api
	@DeleteMapping("/testassets/{id}")
	public ResponseEntity < Map <String, Boolean>> deleteTestasset(@PathVariable(value = "id") Long testassetId)
			throws ResourceNotFoundException {
		Testasset testasset = testassetRepository.findById(testassetId)
				.orElseThrow(() -> new ResourceNotFoundException("Testasset not found for this id :: " + testassetId));

		testassetRepository.delete(testasset);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
