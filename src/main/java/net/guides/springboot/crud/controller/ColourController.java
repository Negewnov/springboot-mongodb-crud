package net.guides.springboot.crud.controller;

import net.guides.springboot.crud.exception.ResourceNotFoundException;
import net.guides.springboot.crud.model.Colour;
import net.guides.springboot.crud.repository.ColourRepository;
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
public class ColourController {

	@Autowired
	private ColourRepository colourRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	// get all colours
	@GetMapping("/colours")
	public List<Colour> getAllColours() {
		return colourRepository.findAll();
	}

    // create colour rest api
	@PostMapping("/colours")
	public Colour createColour(@Valid @RequestBody Colour colour) {
		colour.setId(sequenceGeneratorService.generateSequence(Colour.SEQUENCE_NAME));
		return colourRepository.save(colour);
	}

    // get colour by id rest api
	@GetMapping("/colours/{id}")
	public ResponseEntity<Colour> getColourById(@PathVariable(value = "id") Long colourId)
			throws ResourceNotFoundException {
		Colour colour = colourRepository.findById(colourId)
				.orElseThrow(() -> new ResourceNotFoundException("Colour not found for this id :: " + colourId));
		return ResponseEntity.ok().body(colour);
	}

    // update colour rest api

	@PutMapping("/colours/{id}")
	public ResponseEntity<Colour> updateColour(@PathVariable(value = "id") Long colourId,
			@Valid @RequestBody Colour colourDetails) throws ResourceNotFoundException {
		Colour colour = colourRepository.findById(colourId)
				.orElseThrow(() -> new ResourceNotFoundException("Colour not found for this id :: " + colourId));

		colour.setColourName(colourDetails.getColourName());

		final Colour updatedColour = colourRepository.save(colour);
		return ResponseEntity.ok(updatedColour);
	}

    // delete colour rest api
	@DeleteMapping("/colours/{id}")
	public ResponseEntity < Map <String, Boolean>> deleteColour(@PathVariable(value = "id") Long colourId)
			throws ResourceNotFoundException {
		Colour colour = colourRepository.findById(colourId)
				.orElseThrow(() -> new ResourceNotFoundException("Colour not found for this id :: " + colourId));

		colourRepository.delete(colour);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
