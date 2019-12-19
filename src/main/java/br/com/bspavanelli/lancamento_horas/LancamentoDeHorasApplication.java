package br.com.bspavanelli.lancamento_horas;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.bspavanelli.lancamento_horas.entities.Feriado;
import br.com.bspavanelli.lancamento_horas.entities.Usuario;
import br.com.bspavanelli.lancamento_horas.repositories.FeriadoRepository;
import br.com.bspavanelli.lancamento_horas.repositories.UsuarioRepository;

@SpringBootApplication
public class LancamentoDeHorasApplication implements CommandLineRunner {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	FeriadoRepository feriadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(LancamentoDeHorasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario1 = new Usuario(null, "Bruno dos Santos Pavanelli", "bspavanelli@gmail.com", "123456");
		Usuario usuario2 = new Usuario(null, "Nathalia de Souza Lombardi Pavanelli", "nah.lombardi05@gmail.com",
				"456789");
		Usuario usuario3 = new Usuario(null, "Giovana Lombardi Moreno Correia", "gilombardi@gmail.com", "789456");
		Usuario usuario4 = new Usuario(null, "João Pavanelli Júnior", "joaopavanelli@gmail.com", "456123");
		Usuario usuario5 = new Usuario(null, "Elza Aparecida dos Santos Pavanelli", "elzapavanelli@gmail.com",
				"789123");

		usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5));

		Feriado feriado1 = new Feriado(null, new GregorianCalendar(2019, 11, 25), true);
		Feriado feriado2 = new Feriado(null, new GregorianCalendar(2020, 00, 01), true);
		Feriado feriado3 = new Feriado(null, new GregorianCalendar(2020, 01, 16), false);

		feriadoRepository.saveAll(Arrays.asList(feriado1, feriado2, feriado3));

	}

}
