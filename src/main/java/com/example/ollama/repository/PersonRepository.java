package com.example.ollama.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ollama.entity.Person;

/**
 * Repository interface for Person entity.
 * Provides CRUD operations and custom query methods.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // JpaRepository provides all basic CRUD operations
    // Additional custom query methods can be added here if needed
}
