package anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Para poner la anotaci�n en Javadocs
@Documented

//Cuando la anotaci�n es necesaria. SOURCE: descartardo durante la compilaci�n, no se escribir� en el bytecode. CLASS: Descartada durante la carga de la clase; �til cuando se hace el bytecode-level post-processing. RUNTIME: No se descarta; la anotaci�n estar� disponible en tiempo de ejecuci�n mediante reflection
@Retention(RetentionPolicy.RUNTIME)

//Lugar donde puede ir la anotaci�n
@Target(ElementType.FIELD)
public @interface FrutoSeco {
	boolean tieneVitaminaE() default false;
	
	int calorias();
	
	String arbolQueDaEsteFruto() default "";	
}