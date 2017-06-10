package reserva;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

import org.junit.experimental.theories.Theories;

public class Reserva {
	// Usar DFS
	private int cantidadMiradores;
	private int cantidadTramos;
	private int cantidadCaminos = 0;
	private Stack<Integer> pila;
	private int[] inicio;
	private int[] destino;

	public Reserva(String path) {
		try {
			leerArchivo(path);
		} catch (Exception e) {
			System.out.println("Error abrir archivo.");
			e.printStackTrace();
		}
	}

	public void leerArchivo(String path) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
			this.cantidadMiradores = sc.nextInt();
			this.cantidadTramos = sc.nextInt();
			this.inicio = new int[this.cantidadTramos];
			this.destino = new int[this.cantidadTramos];
			this.pila = new Stack<Integer>();
			for (int i = 0; i < this.cantidadTramos; i++) {
				this.inicio[i] = sc.nextInt();
				this.destino[i] = sc.nextInt();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		sc.close();
	}

	public void escribirVector() {
		for (int i = 0; i < this.cantidadTramos; i++)
			System.out.println(this.inicio[i] + " " + this.destino[i] + "\n");
	}

	public void grabarArchivo(String path) {
		PrintWriter salida;
		try {
			salida = new PrintWriter(new FileWriter(path));
			salida.println(this.cantidadCaminos);

			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
