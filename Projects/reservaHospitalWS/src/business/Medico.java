package business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.orm.PersistentException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import orm.Hora_medicaCriteria;
import vo.HoraMedicaVo;
import vo.ReservaVo;

public class Medico {

	/*Utiliza la misma mecánica que ReservarHoraAps pero esta vez,
	 *pasaremos como parametro un arreglo de horas de control en caso de
	 *que se quiera reservar mas de una hora continua con un mismo paciente.
	 *La clave de esto es contar la cantidad de Ids de hora de control que tenemos
	 *para poder asignar la cantidad de horas correspondientes a la reserva.
	 *verificando que la hora medica no sea nula y que la reserva tenga al paciente adecuado.
	 * 
	*/
	public String ReservarHoraMedicaControl(int idHoraControl[], int idPaciente) {
	
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		List<orm.Hora_medica> horas = new ArrayList<orm.Hora_medica>();
		try {
	
			orm.Paciente pa = orm.PacienteDAO.getPacienteByORMID(idPaciente);
			orm.Reserva re = null;
			ReservaVo reVo = null;
	
			for (int i = 0; i < idHoraControl.length; i++) {
	
				orm.Hora_medica hm = orm.Hora_medicaDAO
						.getHora_medicaByORMID(idHoraControl[i]);
				if (hm == null || hm.reserva.size() != 0)
					return null;
				horas.add(hm);
	
			}
	
			if (pa != null) {
	
				re = new orm.Reserva();
				re.setPaciente(pa);
				re.setPersona(pa.getPersona());
				for (int i = 0; i < horas.size(); i++) {
					re.hora_medica.add(horas.get(i));
				}
				orm.ReservaDAO.save(re);
				orm.ReservaDAO.refresh(re);
				reVo = ReservaVo.fromORM(re);
				return g.toJson(reVo);
	
			}
	
		} catch (PersistentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return null;
	}

	public String buscarSuDisponibilidadHora(int idMedico, Date f1, Date f2) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		List<HoraMedicaVo> lhoras = new ArrayList<HoraMedicaVo>();
		try {
			Hora_medicaCriteria c = new Hora_medicaCriteria();
			c.f_inicio.between(new Timestamp(f1.getTime()),
					new Timestamp(f2.getTime()));
			c.reserva.isEmpty();
			@SuppressWarnings("unchecked")
			List<orm.Hora_medica> horas = c.list();
			for (int i = 0; i < horas.size(); i++) {
				HoraMedicaVo hmed = HoraMedicaVo.fromORM(horas.get(i));
				lhoras.add(hmed);
			}// end for
			return g.toJson(lhoras);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}