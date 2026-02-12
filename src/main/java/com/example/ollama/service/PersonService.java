package com.example.ollama.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.ollama.entity.Person;
import com.example.ollama.repository.PersonRepository;

/**
 * Service class for Person entity.
 * Contains business logic for Person operations.
 */
@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Retrieve all persons from the database.
     * @return List of all persons
     */
    public List<Person> getAllPersons() {
        logger.info("Fetching all persons from database");
        List<Person> persons = personRepository.findAll();
        logger.info("Found {} persons", persons.size());
        return persons;
    }

    /**
     * Retrieve a person by ID.
     * @param id Person ID
     * @return Optional containing the person if found
     */
    public Optional<Person> getPersonById(Integer id) {
        logger.info("Fetching person with ID: {}", id);
        return personRepository.findById(id);
    }

    /**
     * Save a new person or update an existing one.
     * @param person Person to save
     * @return Saved person
     */
    public Person savePerson(Person person) {
        logger.info("Saving person: {}", person);
        return personRepository.save(person);
    }

    /**
     * Delete a person by ID.
     * @param id Person ID to delete
     */
    public void deletePerson(Integer id) {
        logger.info("Deleting person with ID: {}", id);
        personRepository.deleteById(id);
    }
}
