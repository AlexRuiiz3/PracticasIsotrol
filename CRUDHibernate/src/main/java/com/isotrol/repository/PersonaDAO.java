package com.isotrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isotrol.entity.Persona;

public interface PersonaDAO extends JpaRepository<Persona, Integer>{

}
