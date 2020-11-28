package net.guides.springboot.crud.controller;

import net.guides.springboot.crud.exception.ResourceNotFoundException;
import net.guides.springboot.crud.model.Membership;
import net.guides.springboot.crud.repository.MembershipRepository;
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
public class MembershipController {

	@Autowired
	private MembershipRepository membershipRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	// get all memberships
	@GetMapping("/memberships")
	public List<Membership> getAllMemberships() {
		return membershipRepository.findAll();
	}

    // create membership rest api
	@PostMapping("/memberships")
	public Membership createMembership(@Valid @RequestBody Membership membership) {
		membership.setId(sequenceGeneratorService.generateSequence(Membership.SEQUENCE_NAME));
		return membershipRepository.save(membership);
	}

    // get membership by id rest api
	@GetMapping("/memberships/{id}")
	public ResponseEntity<Membership> getMembershipById(@PathVariable(value = "id") Long membershipId)
			throws ResourceNotFoundException {
		Membership membership = membershipRepository.findById(membershipId)
				.orElseThrow(() -> new ResourceNotFoundException("Membership not found for this id :: " + membershipId));
		return ResponseEntity.ok().body(membership);
	}

    // update membership rest api

	@PutMapping("/memberships/{id}")
	public ResponseEntity<Membership> updateMembership(@PathVariable(value = "id") Long membershipId,
			@Valid @RequestBody Membership membershipDetails) throws ResourceNotFoundException {
		Membership membership = membershipRepository.findById(membershipId)
				.orElseThrow(() -> new ResourceNotFoundException("Membership not found for this id :: " + membershipId));

		membership.setMembershipName(membershipDetails.getMembershipName());

		final Membership updatedMembership = membershipRepository.save(membership);
		return ResponseEntity.ok(updatedMembership);
	}

    // delete membership rest api
	@DeleteMapping("/memberships/{id}")
	public ResponseEntity < Map <String, Boolean>> deleteMembership(@PathVariable(value = "id") Long membershipId)
			throws ResourceNotFoundException {
		Membership membership = membershipRepository.findById(membershipId)
				.orElseThrow(() -> new ResourceNotFoundException("Membership not found for this id :: " + membershipId));

		membershipRepository.delete(membership);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
