package reserva;

import org.junit.Test;

public class ReservaTest {

	private static String archivoIn = "Preparacion de Prueba/Lote de Prueba/Entrada/";
	private static String archivoOut = "Ejecucion de Prueba/Salida Obtenida/";

	@Test
	public void testEnunciado() {
		Reserva reserva = new Reserva(archivoIn + "00_Enunciado.in");
		reserva.calcula();
		reserva.escribirCantidad();
	}

}
