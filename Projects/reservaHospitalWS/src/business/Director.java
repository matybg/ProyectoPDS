package business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.orm.PersistentException;

import orm.BoxCriteria;
import orm.Hora_medicaCriteria;
import orm.MedicoCriteria;
import orm.PacienteCriteria;
import orm.ReservaCriteria;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import vo.BoxVo;
import vo.EspecialidadVo;
import vo.MedicoVo;
import vo.PacienteVo;
import vo.PersonaVo;

public class Director {

	public int obtenerPorcentajeOcupacionBox(int idBox, Date fecha1, Date fecha2) {
		try {
			//Generamos una criteria de reservas al vincular las criterias de reserva y horas medicas
			//y filtramos por una fecha inicio y una fecha fin.
			//donde exista el box que nosotros pasamos como parámetro.
			ReservaCriteria resCriteria = new ReservaCriteria();
			Hora_medicaCriteria horaCriteria = resCriteria
					.createHora_medicaCriteria();
			horaCriteria.f_inicio.between(new Timestamp(fecha1.getTime()),
					new Timestamp(fecha2.getTime()));
			BoxCriteria boxCriteria = horaCriteria.createBoxCriteria();
			boxCriteria.id.eq(idBox);
	
			//Generamos una nueva criteria con la misma mecanica anterior,
			//pero esta vez, sera con las horas medicas del mismo box, no las reservas.
			Hora_medicaCriteria hCriteria2 = new Hora_medicaCriteria();
			hCriteria2.f_inicio.between(new Timestamp(fecha1.getTime()),
					new Timestamp(fecha2.getTime()));
			BoxCriteria bCriteria2 = hCriteria2.createBoxCriteria();
			bCriteria2.id.eq(idBox);
	
			// Cantidad de reservas del box
			int res = resCriteria.list().size();
			// Cantidad de horas asignadas al box
			int hmb = hCriteria2.list().size();
	
			if (hmb == 0) {
				return 0;
			} else {
				return (int) res * 100 / hmb;
			}
	
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public int obtenerPorcentajeOcupacionMedico(int idMedico, Date fecha1,
			Date fecha2) {
		try {
			//crea un reservas criteria al vincular 
			//las horas medicas y las reservas.
			//filtrando por fecha1 y fecha 2
			//donde el medico criteria sera igual a id del medico que pasamos como parámetro
			
			ReservaCriteria resCriteria = new ReservaCriteria();
			Hora_medicaCriteria horaCriteria = resCriteria
					.createHora_medicaCriteria();
			horaCriteria.f_inicio.between(new Timestamp(fecha1.getTime()),
					new Timestamp(fecha2.getTime()));
			MedicoCriteria medicoCriteria = horaCriteria.createMedicoCriteria();
			medicoCriteria.id.eq(idMedico);
	
			//luego generamos un nuevo hora medica criteria 
			//y nuevamente obtenemos los medicos relacionados al parámetro
			Hora_medicaCriteria hCriteria = new Hora_medicaCriteria();
			hCriteria.f_inicio.between(new Timestamp(fecha1.getTime()),
					new Timestamp(fecha2.getTime()));
			MedicoCriteria mCriteria = hCriteria.createMedicoCriteria();
			mCriteria.id.eq(idMedico);
	
			// Cantidad de reservas del Medico
			int res = resCriteria.list().size();
			// Cantidad de horas asignadas al Medico
			int hmm = mCriteria.list().size();
	
			int porcentaje = res * 100 / hmm;
			if (hmm == 0) {
				return 0;
			}
	
			return porcentaje;
	
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public String obtenerMedicosMasSolicitados(Date t1, Date t2) {
		//Gson g = new Gson();
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			//Traer medicos que han reservado ordenados por id.
			MedicoCriteria mc = new MedicoCriteria();
			Hora_medicaCriteria hmc = mc.createHora_medicaCriteria();
			hmc.f_inicio.between(new Timestamp(t1.getTime()), new Timestamp(t2.getTime()));
			ReservaCriteria rc = hmc.createReservaCriteria();
			rc.hora_medica.isNotEmpty();;
			mc.addOrder(Order.asc("id"));
			
			@SuppressWarnings("unchecked")
			List<orm.Medico> medicos = mc.list();
			List<MedReservas> lMedRes = new ArrayList<MedReservas>();
			
			
			// Cuenta de la cantidad de reservas de cada paciente y las asocia a este.
			int count = 0;
			int current_id = 0;
			
			if( medicos.size() > 0 ){ 										// Si existen medicos con reservas. 
				
				current_id = medicos.get(0).getId(); 							// El id actual es el del primer medico;
				for(int i=0; i<medicos.size(); i++){
					
					if(medicos.get(i).getId() != current_id || i == medicos.size()-1 ){	// Sigo contando a menos que el id cambie.
						if( i == medicos.size()-1 ){							// Si añadimos el ultimo medico debemos incrementar
							count++;											// en 1+ la cuenta.
						}
						MedicoVo med = MedicoVo.fromORM(medicos.get(i-1)); // Creo Un objeto MedReservas para convertirlo a JSON luego.
						int porcentaje = (int) (count * 100)/medicos.size();
						lMedRes.add(new MedReservas(porcentaje, med));
						count = 1;												// Reseteo el contador a  para el id siguiente.
						current_id = medicos.get(i).getId();
					}
					else{
						count++;
					}
				}// fin for
			}
			// Ordenar por cantidad de reservas
			Collections.sort(lMedRes);
			Collections.reverse(lMedRes);  // invertir orden de mayor a menor
			
			return g.toJson(lMedRes);
			
		} catch (PersistentException e) {
			e.printStackTrace();
		}		
		return null;
	}

	public String obtenerPacientesMasAtendido(Date f1, Date f2) {

		// Gson g = new Gson();
		Gson g = new GsonBuilder().setPrettyPrinting().create();

		try {
			// Traer pacientes que han reservado ordenados por id.
			PacienteCriteria pc = new PacienteCriteria();
			ReservaCriteria rc = pc.createReservaCriteria();
			Hora_medicaCriteria hmc = rc.createHora_medicaCriteria();
			//entre la fecha 1 y la fecha 2
			hmc.f_inicio.between(new Timestamp(f1.getTime()),
					new Timestamp(f2.getTime()));
			//ordenados ascendentemente
			pc.addOrder(Order.asc("id"));

			@SuppressWarnings("unchecked")
			//y guardados en una lista
			List<orm.Paciente> pacientes = pc.list();
			List<PacReservas> lPacRes = new ArrayList<PacReservas>();

			// Cuenta de la cantidad de reservas de cada paciente y las asocia a
			// este.
			int count = 0;
			int current_id = 0;

			if (pacientes.size() > 0) { // Si existen pacientes que con
										// reservas.

				current_id = pacientes.get(0).getId(); // El id actual es el del
														// primer paciente;
				for (int i = 0; i < pacientes.size(); i++) {

					if (pacientes.get(i).getId() != current_id
							|| i == pacientes.size() - 1) { // Sigue contando a menos que el id cambie.
						if (i == pacientes.size() - 1) { // Si añadimos el
															// ultimo paciente debemos incrementar en 1+ la cuenta.
							count++;
						}
						PacienteVo pa = PacienteVo
								.fromORM(pacientes.get(i - 1)); // Crea un objeto PacReservas para convertirlo a JSON luego.
						int porcentaje = (int) (count * 100) / pacientes.size();
						lPacRes.add(new PacReservas(porcentaje, pa));
						count = 1; // Reseteo el contador a para el id
									// siguiente.
						current_id = pacientes.get(i).getId();
					} else {
						count++;
					}
				}// fin for
			}

			// Ordenar por cantidad de reservas
			Collections.sort(lPacRes);
			Collections.reverse(lPacRes); // invertir orden de mayor a menor

			return g.toJson(lPacRes);

		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String obtenerMedicos() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<MedicoVo> medicoVOs = new ArrayList<MedicoVo>();
		try {
			//obtiene la lista de medicos a través de una query
			orm.Medico[] medicos = orm.MedicoDAO.listMedicoByQuery(null, null);

			for (int i = 0; i < medicos.length; i++) {
				PersonaVo personaVO = PersonaVo
						.fromORM(medicos[i].getPersona());
				EspecialidadVo especialidadVO = EspecialidadVo
						.fromORM(medicos[i].getEspecialidad());
				//Y devolvera una lista de medicoVOs ingresados con el medico y 
				//su persona asociada ademas de su especialidad.
				medicoVOs.add(new MedicoVo(medicos[i].getId(), personaVO,
						especialidadVO));
			}
			String out = gson.toJson(medicoVOs);
			return out;

		} catch (PersistentException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public String obtenerBoxs() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<BoxVo> boxVOs = new ArrayList<BoxVo>();
		
		try {
			//obtiene un array de boxes a traves de una query
			orm.Box[] boxs = orm.BoxDAO.listBoxByQuery(null, null);
			
			for (int i = 0; i < boxs.length; i++) {
				boxVOs.add(new BoxVo(boxs[i].getId(), boxs[i].getNombre()));
			}
			String out = gson.toJson(boxVOs);
			return out;
	
		} catch (PersistentException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	
	}

	// Implementa una nueva sub clase para hacer comparables los objetos de tipo
	// paciente y reservas
	// Es un utilitario para el WS ObtenerPacienteMasAtendido.
	final class PacReservas implements Comparable<PacReservas> {
		private int reservas;
		private PacienteVo paciente;
	
		public PacReservas(int reservas, PacienteVo paciente) {
			this.reservas = reservas;
			this.paciente = paciente;
		}
	
		public PacienteVo getPaciente() {
			return this.paciente;
		}
	
		public int getReservas() {
			return this.reservas;
		}
	
		public int compareTo(PacReservas other) {
			// le da la propiedad de comparables a las reservas para su
			//posterior contabilización.
			return this.reservas - other.reservas;
		}
	
	}
	
	// Implementa una nueva sub clase para hacer comparables los objetos de tipo
	// medico y reservas
	// Es un utilitario para el WS ObtenerMedicoMasSolicitado.
	final class MedReservas implements Comparable<MedReservas>{
		private int reservas;
		private MedicoVo medico;
		
		public MedReservas(int reservas, MedicoVo medico){
			this.reservas = reservas;
			this.medico = medico;
		}
		
		public MedicoVo getMedico(){ return this.medico; }
		public int getReservas(){ return this.reservas; }
		
		public int compareTo(MedReservas other){
			// le da la propiedad de comparables a las reservas para su
			//posterior contabilización.
			// retornar < 0 si este es menor que other
			// 0 si igual o > 0 i mayor que otro.
			return this.reservas - other.reservas;
		}
		
	}
}
