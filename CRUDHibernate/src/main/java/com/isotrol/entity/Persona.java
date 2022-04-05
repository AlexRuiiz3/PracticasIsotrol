package com.isotrol.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * The persistent class for the personas database table.
 * 
 */
@Entity
@Table(name="personas")
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@NotEmpty(message="Los apellidos no pueden estar vacios")
	@Column(nullable=false, length=45)
	private String apellidos;

	//@NotEmpty(message="Debe ingresar alguna edad")
	@Column(nullable=false)
	private short edad;

	@NotEmpty(message="El email no puede estar vacio")
	@Column(nullable=false, length=45)
	private String email;

	@NotEmpty(message="El nombre no puede estar vacio")
	@Column(nullable=false, length=45)
	private String nombre;

	@Column(length=9)
	private String telefono;

	public Persona() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public short getEdad() {
		return this.edad;
	}

	public void setEdad(short edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}