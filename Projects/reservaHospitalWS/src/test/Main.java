package test;

import business.Paciente;
public class Main {

	//Genera una prueba por consola de una consulta al m�todo obtenerMedicosDeUnaEspecialidad
	//con el id n�mero 1 (especialidad: Pediatr�a)
	//Retorna el o los m�dicos asociados a esa especialidad.
	public static void main(String[] args) {
		Paciente esp = new Paciente();
		System.out.print(esp.obtenerMedicosDeUnaEspecialidad(1));

	}
}
