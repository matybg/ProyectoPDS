/**
 * Licensee: Universidad de La Frontera
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateReservaHospData {
	public void createTestData() throws PersistentException {
		// Insert sample data
		PersistentSession session = orm.ReservaHospPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			session.doWork(new org.hibernate.jdbc.Work() {
				public void execute(java.sql.Connection conn) throws java.sql.SQLException {
					java.sql.Statement statement = conn.createStatement();
					statement.executeUpdate("INSERT INTO persona(nombres, apellidos, rut, f_nac, telefono, direccion, ciudad, email, activo) VALUES ('Matías', 'Barrera', '17727287', '1991-04-10', '94922332', 'San Martín 01220', 'Temuco', 'maty.bg@gmail.com', 1)");
					statement.executeUpdate("INSERT INTO persona(nombres, apellidos, rut, f_nac, telefono, direccion, ciudad, email, activo) VALUES ('Pepito', 'Medico', '144234234', '1985-07-10', '23452354', 'Las Encinas 2312412', 'Temuco', 'Pepito@gmail.com', 1)");
					statement.executeUpdate("INSERT INTO persona(nombres, apellidos, rut, f_nac, telefono, direccion, ciudad, email, activo) VALUES ('Ismaelito', 'Pacientecito', '17263423', '1990-05-20', '34124124', 'Estadio 23242', 'Temuco', 'Ismagae@gmail.com', 1)");
					statement.executeUpdate("INSERT INTO persona(nombres, apellidos, rut, f_nac, telefono, direccion, ciudad, email, activo) VALUES ('Raulito', 'Enfermito', '32423224', '1980-04-12', '42324223', 'Pauline 23242', 'Temuco', 'raulito@gmail.com', 1)");
					statement.executeUpdate("INSERT INTO paciente(persona_id) VALUES (3)");
					statement.executeUpdate("INSERT INTO paciente(persona_id) VALUES (4)");
					statement.executeUpdate("INSERT INTO director(persona_id) VALUES (1)");
					statement.executeUpdate("INSERT INTO especialidad(nombre) VALUES ('Pediatría')");
					statement.executeUpdate("INSERT INTO especialidad(nombre) VALUES ('Oftanmología')");
					statement.executeUpdate("INSERT INTO medico(persona_id, especialidad_id) VALUES (2, 1)");
					statement.executeUpdate("INSERT INTO reserva(persona_id, paciente_id) VALUES (3, 1)");
					statement.executeUpdate("INSERT INTO reserva(persona_id, paciente_id) VALUES (3, 1)");
					statement.executeUpdate("INSERT INTO reserva(persona_id, paciente_id) VALUES (4, 2)");
					statement.executeUpdate("INSERT INTO box(nombre) VALUES ('Box 1')");
					statement.executeUpdate("INSERT INTO box(nombre) VALUES ('Box 2')");
					statement.executeUpdate("INSERT INTO hora_medica(medico_id, box_id, f_inicio, f_fin) VALUES (1, 1, '2015-01-10 10:00:00', '2015-01-10 10:15:00')");
					statement.executeUpdate("INSERT INTO hora_medica(medico_id, box_id, f_inicio, f_fin) VALUES (1, 2, '2015-01-10 10:15:00', '2015-01-10 10:30:00')");
					statement.executeUpdate("INSERT INTO hora_medica(medico_id, box_id, f_inicio, f_fin) VALUES (1, 1, '2015-01-10 10:30:00', '2015-01-10 10:45:00')");
					statement.executeUpdate("INSERT INTO hora_medica(medico_id, box_id, f_inicio, f_fin) VALUES (1, 1, '2015-01-10 10:45:00', '2015-01-10 11:00:00')");
					statement.executeUpdate("INSERT INTO hora_medica(medico_id, box_id, f_inicio, f_fin) VALUES (1, 1, '2015-01-10 11:00:00', '2015-01-10 11:15:00')");
					statement.executeUpdate("INSERT INTO hora_medica(medico_id, box_id, f_inicio, f_fin) VALUES (1, 1, '2015-01-10 11:15:00', '2015-01-10 11:30:00')");
					statement.executeUpdate("INSERT INTO hora_medica_reserva(hora_medica_id, reserva_id) VALUES (1, 1)");
					statement.executeUpdate("INSERT INTO hora_medica_reserva(hora_medica_id, reserva_id) VALUES (2, 2)");
					statement.executeUpdate("INSERT INTO hora_medica_reserva(hora_medica_id, reserva_id) VALUES (3, 3)");
					statement.close();
				}
			});
			transaction.commit();
		}
		catch (Exception e) {
			try {
				transaction.rollback();
			}
			catch (PersistentException e1) {
				e.printStackTrace();
			}
			e.printStackTrace();
		}
		
		PersistentTransaction t = orm.ReservaHospPersistentManager.instance().getSession().beginTransaction();
		try {
			orm.Persona lormPersona = orm.PersonaDAO.createPersona();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : reserva, medico, director, paciente, activo, ciudad, direccion, f_nac, rut, apellidos, nombres
			orm.PersonaDAO.save(lormPersona);
			orm.Paciente lormPaciente = orm.PacienteDAO.createPaciente();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : reserva, persona
			orm.PacienteDAO.save(lormPaciente);
			orm.Director lormDirector = orm.DirectorDAO.createDirector();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : persona
			orm.DirectorDAO.save(lormDirector);
			orm.Medico lormMedico = orm.MedicoDAO.createMedico();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : hora_medica, persona
			orm.MedicoDAO.save(lormMedico);
			orm.Especialidad lormEspecialidad = orm.EspecialidadDAO.createEspecialidad();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : nombre
			orm.EspecialidadDAO.save(lormEspecialidad);
			orm.Reserva lormReserva = orm.ReservaDAO.createReserva();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : hora_medica, paciente, persona
			orm.ReservaDAO.save(lormReserva);
			orm.Hora_medica lormHora_medica = orm.Hora_medicaDAO.createHora_medica();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : reserva, f_fin, f_inicio, box, medico
			orm.Hora_medicaDAO.save(lormHora_medica);
			orm.Box lormBox = orm.BoxDAO.createBox();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : hora_medica, nombre
			orm.BoxDAO.save(lormBox);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateReservaHospData createReservaHospData = new CreateReservaHospData();
			try {
				createReservaHospData.createTestData();
			}
			finally {
				orm.ReservaHospPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
