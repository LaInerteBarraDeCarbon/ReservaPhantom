package reserva;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

public class Reserva {
	// Usar DFS
	private int cantidadMiradores;
	private int cantidadTramos;
	private int[] inicio;
	private int[] destino;
	private int[] valores;

	public Reserva(String path) {
		try {
			leerArchivo(path);
		} catch (Exception e) {
			System.out.println("Error abrir archivo.");
			e.printStackTrace();
		}
	}

	public void calcula() {
		int cantidadParaValuar = this.cantidadMiradores - 1;
		this.valores[0] = 1;

		while (cantidadParaValuar > 0) {
			int i = 1;
			while (cantidadParaValuar > 0 && i < this.cantidadMiradores) {
				if (this.valores[i] == 0 && puedoValuar(i + 1)) {
					this.valores[i] = sumaPadres(i + 1);
					cantidadParaValuar--;
				}
				
				i++;
			}
		}
	}

	public boolean puedoValuar(int m) {
		for (int i = 0; i < this.cantidadTramos; i++) {
			if (this.destino[i] == m && this.valores[this.inicio[i] - 1] == 0)
				return false;
		}
		return true;
	}

	public int sumaPadres(int m) {
		int suma = 0;
		for (int i = 0; i < this.cantidadTramos; i++) {
			if (this.destino[i] == m && this.valores[this.inicio[i] - 1] != 0)
				suma += this.valores[this.inicio[i] - 1];
		}
		return suma;
	}

	public void leerArchivo(String path) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
			this.cantidadMiradores = sc.nextInt();
			this.cantidadTramos = sc.nextInt();
			this.inicio = new int[this.cantidadTramos];
			this.destino = new int[this.cantidadTramos];
			this.valores = new int[this.cantidadMiradores];
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
	
	public void escribirFinal(){
		System.out.println(this.valores[this.cantidadMiradores - 1]);
	}

	public void grabarArchivo(String path) {
		PrintWriter salida;
		try {
			salida = new PrintWriter(new FileWriter(path));
			salida.println(this.valores[this.cantidadMiradores - 1]);

			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
