
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

````markdown
# Configuración de Entorno Python para ANTLR en Kali Linux

En Kali Linux, el entorno Python es **externally managed**, lo que significa que no se pueden instalar paquetes globales con `pip` directamente. Para trabajar con ANTLR en Python de forma segura, se debe usar un **entorno virtual**. A continuación, los pasos con explicación:
````
## 1. Instalar el paquete `python3-venv`
  Esto debido a que yo cuenta con la distribucion kali-linux, en otras casos desconosco si a de ser necesario.

```bash
sudo apt update
sudo apt install python3.13-venv
````

**Explicación:**

* Kali no incluye por defecto el módulo `venv`.
* Este módulo permite crear entornos virtuales aislados.
* Ajusta la versión `python3.13-venv` según la versión de Python que tengas instalada.

---

## 2. Crear un entorno virtual

```bash
python3 -m venv venv
```

**Explicación:**

* `venv` crea un directorio (`venv`) con una copia aislada de Python.
* Permite instalar librerías sin afectar el sistema global.
* Todos los paquetes instalados dentro de este entorno quedan aislados.

---

## 3. Activar el entorno virtual

```bash
source venv/bin/activate
```

**Explicación:**

* Activa el entorno virtual.
* El prompt cambia para indicar `(venv)`.
* Ahora cualquier paquete que instales usando `pip` se instalará dentro del entorno, sin afectar el sistema principal.

---

## 4. Actualizar pip y instalar ANTLR runtime

```bash
pip install --upgrade pip
pip install antlr4-python3-runtime
```

**Explicación:**

* `pip install --upgrade pip` asegura que la versión de pip sea la más reciente dentro del entorno virtual.
* `antlr4-python3-runtime` es la librería necesaria para ejecutar los lexer/parser generados por ANTLR en Python.

---

## 5. Ejecutar tu calculadora en Python

```bash
python CalculadoraPython.py Prueba1.expr
```

**Explicación:**

* Ejecuta tu código de calculadora usando el entorno virtual.
* Todas las dependencias instaladas dentro del entorno virtual están disponibles.
* Garantiza que no se rompa ninguna dependencia del sistema.

---

## 6. Desactivar el entorno virtual (opcional)

```bash
deactivate
```

**Explicación:**

* Vuelve al Python global del sistema.
* El entorno virtual sigue existiendo y puede activarse nuevamente cuando se necesite.

alias antlrpy='java -Xmx500M -cp "/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool -Dlanguage=Python3 -no-listener -visitor'


