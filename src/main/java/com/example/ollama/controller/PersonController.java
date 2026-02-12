package com.example.ollama.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ollama.entity.Person;
import com.example.ollama.service.PersonService;

/**
 * REST Controller for Person entity.
 * Provides endpoints to perform CRUD operations on Persons table.
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * GET /api/persons - Retrieve all persons
     * @return List of all persons
     */
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        logger.info("GET /api/persons - Fetching all persons");
        try {
            List<Person> persons = personService.getAllPersons();
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            logger.error("Error fetching all persons: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * GET /api/persons/{id} - Retrieve a person by ID
     * @param id Person ID
     * @return Person if found, 404 otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        logger.info("GET /api/persons/{} - Fetching person by ID", id);
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Person with ID {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * POST /api/persons - Create a new person
     * @param person Person to create
     * @return Created person
     */
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        logger.info("POST /api/persons - Creating new person: {}", person);
        try {
            Person savedPerson = personService.savePerson(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
        } catch (Exception e) {
            logger.error("Error creating person: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * PUT /api/persons/{id} - Update an existing person
     * @param id Person ID
     * @param person Updated person data
     * @return Updated person
     */
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        logger.info("PUT /api/persons/{} - Updating person", id);
        return personService.getPersonById(id)
                .map(existingPerson -> {
                    person.setPersonId(id);
                    Person updatedPerson = personService.savePerson(person);
                    return ResponseEntity.ok(updatedPerson);
                })
                .orElseGet(() -> {
                    logger.warn("Person with ID {} not found for update", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * DELETE /api/persons/{id} - Delete a person by ID
     * @param id Person ID
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePerson(@PathVariable Integer id) {
        logger.info("DELETE /api/persons/{} - Deleting person", id);
        return personService.getPersonById(id)
                .map(person -> {
                    personService.deletePerson(id);
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Person deleted successfully");
                    response.put("id", id.toString());
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    logger.warn("Person with ID {} not found for deletion", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * GET /api/persons/health - Health check endpoint
     * @return Service status
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "Person Service");
        status.put("database", "PostgreSQL");
        return ResponseEntity.ok(status);
    }
}
