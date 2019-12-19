package br.com.bspavanelli.lancamento_horas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.bspavanelli.lancamento_horas.entities.Feriado;

public class Testes {

	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) throws ParseException {
		
		Date date = formatter.parse("2020-12-12");
        Calendar data = Calendar.getInstance();
        data.setTime(date);
				
		List<Feriado> feriados = new ArrayList<>();
		
		Feriado fer1 = new Feriado(null, new GregorianCalendar(2020, 11, 12), true);
		Feriado fer2 = new Feriado(null, new GregorianCalendar(2019, 11, 12), false);
		Feriado fer3 = new Feriado(null, new GregorianCalendar(2020, 11, 13), true);
		Feriado fer4 = new Feriado(null, new GregorianCalendar(2020, 11, 12), false);
		
		feriados.addAll(Arrays.asList(fer1, fer2, fer3, fer4));
		
		
		for (Feriado feriado : feriados) {
			if (feriado.isRecorrente() && data.get(Calendar.MONTH) == feriado.getData().get(Calendar.MONTH) && data.get(Calendar.DAY_OF_MONTH) == feriado.getData().get(Calendar.DAY_OF_MONTH)) {
				System.out.println("Caiu aqui (recorrente)");
				continue;
			}
			if (!feriado.isRecorrente() && data.equals(feriado.getData())) {
				System.out.println("Caiu aqui (não recorrente)");
				continue;
			}
			System.out.println("Caiu aqui (fora dos ifs)");
		}

	}

}
