package main;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import anotations.Dulce;
import anotations.FrutoSeco;

public class Inicio {

	public static void main(String[] args) {

		BolsitaMarcaA bolsitaMarcaA = new BolsitaMarcaA();
		int caloriasTotalesA = contarCaloriasTotalesFrutosSecos(bolsitaMarcaA);
		System.out.println("Calorias totales de los frutos secos de una bolsita de la marca 'A': " + caloriasTotalesA);

		System.out.println("");

		BolsitaMarcaB bolsitaMarcaB = new BolsitaMarcaB();
		int caloriasTotalesB = contarCaloriasTotalesFrutosSecos(bolsitaMarcaB);
		System.out.println("Calorias totales de los frutos secos de una bolsita de la marca 'B': " + caloriasTotalesB);
	
		System.out.println("");
		
		System.out.println("Valor por defecto dentro de la declaraci�n de la anotaci�n Dulce: "+ Dulce.VALOR_POR_DEFECTO);
	}

	/**
	 * Funci�n que cuenta las calor�as totales de cualquier variable anotadas con FrutoSeco
	 * 
	 * @param bolsita
	 *            objeto de la clase Bolsita del que extraer las calor�as de �nicamente los FrutosSecos
	 * @return cantidad de calor�as totales de �nicamente los FrutosSecos
	 */
	public static int contarCaloriasTotalesFrutosSecos(final Object bolsita) {
		Class<?> claseBolsita = bolsita.getClass();

		int caloriasTotales = 0;

		final Field[] variables = claseBolsita.getDeclaredFields(); // Incluye variables privadas
		for (final Field variable : variables) {

			final Annotation anotacionObtenida = variable.getAnnotation(FrutoSeco.class);

			if (anotacionObtenida != null && anotacionObtenida instanceof FrutoSeco) {
				final FrutoSeco anotacionFrutoSeco = (FrutoSeco) anotacionObtenida;

				int calorias = anotacionFrutoSeco.calorias();

				int cantidad = 0;
				try {
					variable.setAccessible(true);
					cantidad = variable.getInt(bolsita);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

				String nombreFrutoSeco = variable.getName();

				System.out.println("	-Hay " + cantidad + " de " + nombreFrutoSeco + ", y cada una tiene " + calorias + " calor�as");

				caloriasTotales += (cantidad * calorias);
			}
		}

		return caloriasTotales;
	}
}
