
# Calculadora de Expresiones Aritméticas con ANTLR

Este proyecto documenta la instalación y configuración de ANTLR en Linux, la creación de una gramática para expresiones aritméticas complejas, la implementación de un visitor en Java para evaluar expresiones y la ejecución de la calculadora, incluyendo funciones trigonométricas, logaritmos y factoriales.

## Requisitos Previos

- Java JDK 8 o superior.
- `curl` o `wget` para descargar archivos.
- Terminal de Linux.

## Instalación de ANTLR

Descargue ANTLR con:

```bash
curl -O https://www.antlr.org/download/antlr-4.13.1-complete.jar
````

Se recomienda mover el archivo `.jar` a una ubicación organizada para mantener la estructura del proyecto.

## Configuración de Variables de Entorno

Establezca el `CLASSPATH` apuntando al `.jar` de ANTLR:

```bash
export CLASSPATH=".:/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH"
```

Defina alias para simplificar los comandos de ANTLR:

```bash
alias antlr4='java -Xmx500M -cp "/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
alias grun='java -Xmx500M -cp "/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'
```

Aplique los cambios:

```bash
source ~/.bashrc
```

## Objetivo del Proyecto

Desarrollar una calculadora capaz de:

* Evaluar funciones trigonométricas: `sin`, `cos`, `tan`.
* Calcular raíces cuadradas: `sqrt`.
* Evaluar logaritmos naturales (`ln`) y base 10 (`log`).
* Manejar conversiones entre grados y radianes.
* Calcular factoriales `!`.
* Evaluar expresiones complejas mediante un visitor en Java.

## Análisis Léxico

* Cada cadena de caracteres se clasifica en tokens: enteros, decimales, operadores y paréntesis.
* Cualquier cadena que no coincida con un patrón definido se marca como token no reconocido.
* Las palabras reservadas (`for`, `while`, `in`, `range`) se gestionan mediante diccionarios o hash.
* Se aplican expresiones regulares y se respeta el principio de la subcadena más larga (por ejemplo, `=` distinto de `==`).

## Análisis Sintáctico

* La gramática `Gramatica_Cal.g4` define reglas como:

```
expr op=('*'|'/') expr # MulDiv
```

* ANTLR genera automáticamente `Lexer`, `Parser` y `Visitor`.
* Compilación y ejecución:

```bash
antlr4 -no-listener -visitor Gramatica_Cal.g4
javac Calculadora.java CalVisitor.java Gramatica_Cal*.java
java Calculadora Prueba1.expr
```
Visualización del Árbol Sintáctico con grun

Para abrir el árbol sintáctico de una expresión usando la interfaz gráfica de ANTLR:
```
grun Gramatica_Cal prog -gui Prueba1.expr

```
**Salida esperada:**

```
-106.61764705882354
0.49999999999999994
0.5591929034707468
5.0
2.302585092994046
2.0
```

## Errores Comunes y Soluciones

| Error                                               | Causa                                                                  | Solución                                              |
| --------------------------------------------------- | ---------------------------------------------------------------------- | ----------------------------------------------------- |
| Tokens no reconocidos (`sin`, `cos`)                | Definiciones en minúscula en la gramática vs. mayúsculas en el visitor | Unificar tokens en mayúsculas                         |
| Factorial no encontrado (`factorial`)               | Método no implementado en `CalVisitor`                                 | Implementar función factorial iterativa               |
| Resultados incorrectos en funciones trigonométricas | Aplicación doble de `Math.toRadians()`                                 | Aplicar conversión una sola vez si se usan grados     |
| Errores de compilación (`cannot find symbol`)       | Clases eliminadas al simplificar la gramática                          | Limpiar visitor y gramática dejando solo lo requerido |

## Consideraciones Adicionales

* La calculadora respeta precedencia de operadores y manejo de paréntesis.
* Incluye manejo básico de errores léxicos y sintácticos.
* Permite extender funciones agregando nuevas reglas en la gramática y el visitor.

## Entrega

* Ejecución práctica: 7 días.
* Parcial: martes siguiente.
* Incluye ejemplos de programas en AWK, gramática regular en Lex, comparación de lenguajes compilados e interpretados y calculadora ANTLR con números complejos y factoriales.

```
