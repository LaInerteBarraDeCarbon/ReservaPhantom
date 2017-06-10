package reserva;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class Reserva {
	// Usar DFS
	private int cantidadMiradores;
	private int cantidadTramos;
	private int cantidadCaminos = 0;
	private Stack<Integer> pila;
	private int[] tratado;
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

	public void calcula() {
		boolean cambio = false;
		this.pila.add(this.inicio[0]);
		this.tratado[0] = 1;

		while (!pila.isEmpty()) {
			int i = 0;
			while (!pila.isEmpty() && i < this.cantidadTramos && this.pila.lastElement() != this.cantidadMiradores) {
				if (this.inicio[i] == this.pila.lastElement() && this.tratado[this.destino[i] - 1] == 0) {
					this.pila.add(this.destino[i]);
					this.tratado[this.destino[i] - 1] = 1;
					cambio = true;
				}

				i++;
			}
			if (!cambio) {
				this.pila.pop();
				this.tratado[this.cantidadMiradores - 1] = 0;
			}
			if (!pila.isEmpty() && this.pila.lastElement() == this.cantidadMiradores) {
				this.cantidadCaminos++;
				this.pila.pop();
			}
			cambio = false;
		}
	}

	public void leerArchivo(String path) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
			this.cantidadMiradores = sc.nextInt();
			this.cantidadTramos = sc.nextInt();
			this.tratado = new int[this.cantidadMiradores];
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
			System.out.println(this.inicio[i] + " " + this.destino[i]);
	}

	public void escribirCantidad() {
		System.out.println(this.cantidadCaminos);
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
