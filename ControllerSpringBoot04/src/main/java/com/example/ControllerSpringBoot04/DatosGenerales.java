package com.example.ControllerSpringBoot04;

import java.util.ArrayList;
import java.util.List;

import com.example.pojo.Persona;

public class DatosGenerales {

	public static List<Persona> personas = new ArrayList<Persona>();
	
	public static void llenarListaPersonas() {
		personas.add(new Persona(1,"Nombre 1", "Apellido 1",20,"Usu1","Cont1"));
		personas.add(new Persona(2,"Nombre 2", "Apellido 2",32,"Usu2","Cont2"));
		personas.add(new Persona(3,"Nombre 3", "Apellido 3",28,"Usu3","Cont3"));
		personas.add(new Persona(4,"Nombre 4", "Apellido 4",41,"Usu4","Cont4"));
	}
}
