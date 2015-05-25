package test;

import business.Paciente;
public class Main {

	//Genera una prueba por consola de una consulta al método obtenerMedicosDeUnaEspecialidad
	//con el id número 1 (especialidad: Pediatría)
	//Retorna el o los médicos asociados a esa especialidad.
	public static void main(String[] args) {
		Paciente esp = new Paciente();
		System.out.print(esp.obtenerMedicosDeUnaEspecialidad(1));

	}
}
