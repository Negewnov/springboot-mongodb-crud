package net.guides.springboot.crud.controller;

import net.guides.springboot.crud.exception.ResourceNotFoundException;
import net.guides.springboot.crud.model.Group;
import net.guides.springboot.crud.repository.GroupRepository;
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
public class GroupController {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	// get all groups
	@GetMapping("/groups")
	public List<Group> getAllGroups() {
		return groupRepository.findAll();
	}

	// create group rest api
	@PostMapping("/groups")
	public Group createGroup(@Valid @RequestBody Group group) {
		group.setId(sequenceGeneratorService.generateSequence(Group.SEQUENCE_NAME));
		return groupRepository.save(group);
	}

	// get group by id rest api
	@GetMapping("/groups/{id}")
	public ResponseEntity<Group> getGroupById(@PathVariable(value = "id") Long groupId)
			throws ResourceNotFoundException {
		Group group = groupRepository.findById(groupId)
				.orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + groupId));
		return ResponseEntity.ok().body(group);
	}

	// update group rest api

	@PutMapping("/groups/{id}")
	public ResponseEntity<Group> updateGroup(@PathVariable(value = "id") Long groupId,
												   @Valid @RequestBody Group groupDetails) throws ResourceNotFoundException {
		Group group = groupRepository.findById(groupId)
				.orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + groupId));

		group.setGroupName(groupDetails.getGroupName());
		group.setGroupColour(groupDetails.getGroupColour());

		final Group updatedGroup = groupRepository.save(group);
		return ResponseEntity.ok(updatedGroup);
	}

	// delete group rest api
	@DeleteMapping("/groups/{id}")
	public ResponseEntity < Map <String, Boolean>> deleteGroup(@PathVariable(value = "id") Long groupId)
			throws ResourceNotFoundException {
		Group group = groupRepository.findById(groupId)
				.orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + groupId));

		groupRepository.delete(group);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
