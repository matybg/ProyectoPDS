package business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.orm.PersistentException;

import orm.Hora_medica;
import orm.Hora_medicaCriteria;
import vo.EspecialidadVo;
import vo.HoraMedicaVo;
import vo.MedicoVo;
import vo.PersonaVo;
import vo.ReservaVo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Paciente {

	/*
	 * Genera un objeto Gson para la conversion String/JSON Luego genera un
	 * arraylist de tipo EspecialidadVO Genera una query de todas las
	 * especialidades en la DB Posteriormente, itera la query y pobla el
	 * ArrayList con los nombres de cada una de las especialidades encontradas
	 */

	/*
	 * Genera una lista con horas medicas, y luego al utilizar un Criteria,
	 * Usamos nuestras condiciones para la query. Luego retornamos la query a
	 * una lista la recorremos y obtenemos una lista de horas disponibles
	 */
	public String buscarHoraAPS(int idMedico, Date fecha1, Date fecha2) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		List<HoraMedicaVo> lHoras = new ArrayList<HoraMedicaVo>();
	
		try {
	
			Hora_medicaCriteria c = new Hora_medicaCriteria();
			c.f_inicio.between(new Timestamp(fecha1.getTime()), new Timestamp(
					fecha2.getTime()));
			c.reserva.isEmpty();
	
			@SuppressWarnings("unchecked")
			List<Hora_medica> horas = c.list();
	
			for (int i = 0; i < horas.size(); i++) {
	
				HoraMedicaVo hmed = HoraMedicaVo.fromORM(horas.get(i));
				lHoras.add(hmed);
	
			}
			String out = g.toJson(lHoras);
	
			return out;
	
		} catch (PersistentException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	/*
	 *Genera una reserva al recibir un id de horaMedicaAps y al paciente que corresponde
	 *Se hacen las validaciones para verificar que la hora médica es válida y el paciente tambien.
	 *Luego utilizando el DAO guardamos nuestros datos en la BD.
	 *Y retornamos los datos de la reserva como String.
	 */
	public String ReservarHoraAps(int idHoraMedicaAps, int idPaciente) {
	
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		try {
	
			orm.Hora_medica hm = orm.Hora_medicaDAO
					.getHora_medicaByORMID(idHoraMedicaAps);
			orm.Paciente pa = orm.PacienteDAO.getPacienteByORMID(idPaciente);
			orm.Reserva re = null;
			ReservaVo reVo = null;
	
			if (hm != null && pa != null) {
				if (hm.reserva.isEmpty()) {
					re = new orm.Reserva();
					re.setPaciente(pa);
					re.setPersona(pa.getPersona());
					re.hora_medica.add(hm);
					orm.ReservaDAO.save(re);
					orm.ReservaDAO.refresh(re);
	
					reVo = ReservaVo.fromORM(re);
					return g.toJson(reVo);
				}
			}
	
		} catch (PersistentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return null;
	}

	/*
	 * Obtiene todos los medicos ligados al id entregado por el consumidor, que
	 * corresponde a una especialidad.
	 */
	public String obtenerMedicosDeUnaEspecialidad(int IdEspecialidad) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		List<MedicoVo> medicoVo = new ArrayList<MedicoVo>();
		try {
			orm.Especialidad especialidad = orm.EspecialidadDAO
					.loadEspecialidadByQuery("id='" + IdEspecialidad + "'",
							null);
			orm.Medico[] ormMedicos = orm.MedicoDAO.listMedicoByQuery(
					"especialidad_id='" + especialidad.getId() + "'", null);
	
			for (int i = 0; i < ormMedicos.length; i++) {
				PersonaVo p = PersonaVo.fromORM(ormMedicos[i].getPersona());
				EspecialidadVo e = EspecialidadVo.fromORM(ormMedicos[i]
						.getEspecialidad());
				medicoVo.add(new MedicoVo(ormMedicos[i].getId(), p, e));
			}
	
			String out = g.toJson(medicoVo);
			return out;
		} catch (PersistentException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	
	}

	public String obtenerEspecialidad() {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		List<EspecialidadVo> especialidadVo = new ArrayList<EspecialidadVo>();

		try {
			orm.Especialidad[] ormEspecialidads = orm.EspecialidadDAO
					.listEspecialidadByQuery(null, null);

			for (int i = 0; i < ormEspecialidads.length; i++) {
				especialidadVo.add(new EspecialidadVo(ormEspecialidads[i]
						.getId(), ormEspecialidads[i].getNombre()));

			}
			String out = g.toJson(especialidadVo);

			return out;

		} catch (PersistentException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}