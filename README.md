# Calculadora de Expresiones Aritméticas con ANTLR

## Introducción

Este proyecto describe la implementación de una calculadora de expresiones aritméticas utilizando ANTLR (ANother Tool for Language Recognition) en un entorno Linux (específicamente Kali Linux para Python). La calculadora es capaz de evaluar expresiones matemáticas complejas, incluyendo operaciones aritméticas básicas, funciones trigonométricas (`sin`, `cos`, `tan`), raíces cuadradas (`sqrt`), logaritmos (`ln`, `log`), factoriales (`!`), y conversiones entre grados y radianes. Se implementa en dos lenguajes objetivo: Java y Python, utilizando una gramática definida en ANTLR para generar lexer, parser y visitor, con un enfoque en el análisis léxico, sintáctico y semántico.

El proyecto detalla la instalación de ANTLR, la configuración del entorno, la generación de código a partir de la gramática `Gramatica_Cal.g4`, y la implementación de un visitor para evaluar expresiones. También incluye soluciones a errores comunes y recomendaciones para extender la funcionalidad.

## Objetivo

Desarrollar una calculadora que pueda:
- Evaluar expresiones aritméticas con operadores básicos (`+`, `-`, `*`, `/`).
- Procesar funciones trigonométricas (`sin`, `cos`, `tan`), raíces cuadradas (`sqrt`), logaritmos naturales (`ln`) y base 10 (`log`).
- Calcular factoriales (`!`) de manera iterativa.
- Manejar conversiones entre grados y radianes para funciones trigonométricas.
- Implementar un visitor en Java y Python para evaluar expresiones parseadas.
- Generar un árbol sintáctico visualizable y manejar errores léxicos y sintácticos básicos.
- Proporcionar una base extensible para agregar nuevas funciones matemáticas.

## Lenguaje Java

### Requisitos
- **Java JDK 8 o superior**: Necesario para ejecutar ANTLR y compilar los archivos generados.
- **`curl` o `wget`**: Para descargar el archivo `antlr-4.13.1-complete.jar`.
- **Terminal de Linux**: Para ejecutar comandos de configuración y compilación.

### Comandos
1. **Descargar ANTLR**:
   ```bash
   curl -O https://www.antlr.org/download/antlr-4.13.1-complete.jar
   ```
2. **Configurar variables de entorno**:
   ```bash
   export CLASSPATH=".:/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH"
   alias antlr4='java -Xmx500M -cp "/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
   alias grun='java -Xmx500M -cp "/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'
   source ~/.bashrc
   ```
3. **Generar lexer, parser y visitor**:
   ```bash
   antlr4 -no-listener -visitor Gramatica_Cal.g4
   ```
4. **Compilar archivos Java**:
   ```bash
   javac Calculadora.java CalVisitor.java Gramatica_Cal*.java
   ```
5. **Ejecutar la calculadora**:
   ```bash
   java Calculadora Prueba1.expr
   ```
6. **Visualizar el árbol sintáctico**:
   ```bash
   grun Gramatica_Cal prog -gui Prueba1.expr
   ```

### Explicación
- **Descarga**: El archivo `antlr-4.13.1-complete.jar` contiene las herramientas necesarias para generar lexer, parser y visitor.
- **Variables de entorno**: El `CLASSPATH` incluye el `.jar` de ANTLR, y los alias `antlr4` y `grun` simplifican la generación de código y la visualización del árbol sintáctico.
- **Generación**: El comando `antlr4` con la opción `-visitor` genera las clases `Gramatica_CalLexer`, `Gramatica_CalParser`, `Gramatica_CalVisitor`, y otras, basadas en la gramática `Gramatica_Cal.g4`.
- **Compilación y ejecución**: Los archivos generados se compilan junto con `Calculadora.java` (punto de entrada) y `CalVisitor.java` (implementación del visitor). La calculadora lee expresiones desde `Prueba1.expr` y las evalúa.
- **Visualización**: `grun` muestra el árbol sintáctico en una interfaz gráfica para depurar la gramática.

### Archivos Modificables
- **`Gramatica_Cal.g4`**: Define las reglas léxicas (tokens como `SIN`, `COS`, números) y sintácticas (expresiones como `expr op=('*'|'/') expr`).
- **`CalVisitor.java`**: Implementa la lógica para evaluar expresiones, usando `Math` para funciones matemáticas y una implementación iterativa para factoriales.
- **`Calculadora.java`**: Contiene el programa principal que configura el lexer, parser y visitor, y procesa el archivo de entrada `Prueba1.expr`.
- **`Pruwbaa1.expr`**: Único archivo que el usuario debe modificar para probar las salidas del programa. Contiene operaciones aritméticas, cada una en una línea separada por un salto de línea (e.g., sin(90)\n5!\n2+3).

### Salida
La ejecución de `java Calculadora Prueba1.expr` produce:
```
-106.61764705882354
0.49999999999999994
0.5591929034707468
5.0
2.302585092994046
2.0
```

### Notas sobre Errores Presentados
| Error | Causa | Solución |
|-------|-------|----------|
| Tokens no reconocidos (`sin`, `cos`) | Tokens definidos en minúsculas en la gramática, pero usados en mayúsculas en el visitor (o viceversa). | Unificar mayúsculas/minúsculas en la gramática y el visitor. |
| Factorial no encontrado | Método `factorial` no implementado en ` discerned | Implementar una función factorial iterativa en `CalVisitor.java`. |
| Resultados incorrectos en funciones trigonométricas | Conversión a radianes aplicada más de una vez. | Aplicar `Math.toRadians()` solo una vez si la entrada está en grados. |
| `cannot find symbol` | Clases generadas eliminadas o gramática simplificada incorrectamente. | Limpiar archivos generados, regenerar con `antlr4`, y recompilar. |

## Lenguaje Python

### Requisitos
- **Python 3.13 o superior**: Necesario para ejecutar la calculadora y el runtime de ANTLR.
- **`python3.13-venv`**: Módulo para crear entornos virtuales, no incluido por defecto en Kali Linux.
- **`curl` o `wget`**: Para descargar ANTLR.
- **Terminal de Linux**: Para ejecutar comandos de configuración y ejecución.
- **`antlr-4.13.1-complete.jar`**: Necesario para generar lexer, parser y visitor en Python.

### Comandos
1. **Instalar `python3.13-venv`**:
   ```bash
   sudo apt update
   sudo apt install python3.13-venv
   ```
2. **Crear y activar entorno virtual**:
   ```bash
   python3 -m venv venv
   source venv/bin/activate
   ```
3. **Actualizar pip e instalar ANTLR runtime**:
   ```bash
   pip install --upgrade pip
   pip install antlr4-python3-runtime
   ```
4. **Crear alias para generar código Python**:
   ```bash
   alias antlrpy='java -Xmx500M -cp "/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool -Dlanguage=Python3 -no-listener -visitor'
   ```
5. **Generar lexer, parser y visitor**:
   ```bash
   antlrpy Gramatica_Cal.g4
   ```
6. **Ejecutar la calculadora**:
   ```bash
   python CalculadoraPython.py Prueba1.expr
   ```
7. **Desactivar entorno virtual (opcional)**:
   ```bash
   deactivate
   ```

### Explicación
- **Instalación de `python3.13-venv`**: Kali Linux no incluye el módulo `venv` por defecto, necesario para crear entornos virtuales.
- **Entorno virtual**: Aísla las dependencias (como `antlr4-python3-runtime`) del sistema global, evitando conflictos en Kali Linux, que tiene un entorno Python "externally managed".
- **ANTLR runtime**: La librería `antlr4-python3-runtime` permite ejecutar los lexer y parser generados en Python.
- **Alias `antlrpy`**: Simplifica la generación de código Python con la opción `-Dlanguage=Python3` para especificar el lenguaje objetivo.
- **Generación y ejecución**: `antlrpy` genera `Gramatica_CalLexer.py`, `Gramatica_CalParser.py`, y `Gramatica_CalVisitor.py`. `CalculadoraPython.py` configura el flujo de análisis y evalúa las expresiones.

### Archivos Modificables
- **`Gramatica_Cal.g4`**: Define las reglas léxicas y sintácticas, igual que en Java.
- **`Gramatica_CalVisitor.py`**: Implementa el visitor en Python, usando el módulo `math` para funciones matemáticas y factoriales.
- **`CalculadoraPython.py`**: Programa principal que configura el lexer, parser, visitor, y procesa `Prueba1.expr`.

### Salida
La ejecución de `python CalculadoraPython.py Prueba1.expr` produce:
```
-106.61764705882354
0.49999999999999994
0.5591929034707468
5.0
2.302585092994046
2.0
```

### Notas sobre Errores Presentados
| Error | Causa | Solución |
|-------|-------|----------|
| `AttributeError: 'Gramatica_CalParser' has no attribute 'NumberContext'` | Intentar acceder a reglas de la gramática como en Java, donde los nombres son diferentes. | Ajustar el visitor para usar los nombres de reglas generados en Python. |
| `AttributeError: 'ProgContext' object has no attribute 'expr'` | No se verifica si `stat` contiene `expr` antes de procesarlo. | Modificar el visitor para recorrer `stat` con `hasattr(stat, 'expr')` y procesar solo las declaraciones con `expr`. |
| Tokens no reconocidos | Inconsistencia en mayúsculas/minúsculas entre gramática y visitor. | Unificar mayúsculas/minúsculas. |
| Módulo `antlr4` no encontrado | No se instaló `antlr4-python3-runtime` en el entorno virtual. | Ejecutar `pip install antlr4-python3-runtime` dentro del entorno virtual. |

## Recomendaciones para la Comprensión del Análisis Léxico, Sintáctico y Semántico

### Análisis Léxico
- **Definición de Tokens**: Define tokens claros en `Gramatica_Cal.g4` usando expresiones regulares (e.g., `NUMBER: [0-9]+ ('.' [0-9]+)?`). Asegúrate de distinguir entre tokens similares (e.g., `=` vs. `==`) usando el principio de la subcadena más larga.
- **Palabras Reservadas**: Usa un diccionario o hash para manejar palabras reservadas (`sin`, `cos`, `for`, `while`) y evitar conflictos con identificadores.
- **Pruebas**: Usa `grun Gramatica_Cal prog -tokens` para verificar que los tokens se generen correctamente a partir de un archivo de entrada.

### Análisis Sintáctico
- **Gramática Clara**: Estructura la gramática para respetar la precedencia de operadores (e.g., `*` y `/` antes de `+` y `-`). Usa etiquetas (e.g., `# MulDiv`) para facilitar el manejo en el visitor.
- **Visualización**: Ejecuta `grun Gramatica_Cal prog -gui` para inspeccionar el árbol sintáctico y detectar errores en las reglas.
- **Depuración**: Simplifica la gramática inicialmente (e.g., solo suma y resta) y luego añade funciones como `sin` o `factorial` para evitar errores complejos.

### Análisis Semántico
- **Implementación del Visitor**: En Java (`CalVisitor.java`) o Python (`Gramatica_CalVisitor.py`), implementa la lógica de evaluación en los métodos `visit<Regla>` (e.g., `visitMulDiv`, `visitSin`). Usa bibliotecas estándar (`Math` en Java, `math` en Python) para funciones matemáticas.
- **Manejo de Errores**: Valida entradas en el visitor (e.g., factorial solo para enteros no negativos) para evitar excepciones en tiempo de ejecución.
- **Pruebas Incremental**: Prueba cada función (e.g., `sin(90)`, `5!`) por separado antes de combinarlas en expresiones complejas.

## Extensión del Proyecto

Para extender la funcionalidad de la calculadora, considera los siguientes pasos:
1. **Agregar Nuevas Funciones**:
   - Modifica `Gramatica_Cal.g4` para incluir nuevas reglas léxicas (e.g., `EXP: 'exp';`) y sintácticas (e.g., `expr: EXP '(' expr ')' # Exp`).
   - Actualiza el visitor (`CalVisitor.java` o `Gramatica_CalVisitor.py`) para implementar la lógica (e.g., `Math.exp` en Java, `math.exp` en Python).
2. **Manejo Avanzado de Errores**:
   - Implementa un manejador de errores personalizado en `Calculadora.java` o `CalculadoraPython.py` para reportar mensajes claros sobre tokens no válidos o divisiones por cero.
3. **Soporte para Variables**:
   - Añade una regla léxica para identificadores (e.g., `ID: [a-zA-Z]+;`) y una tabla de símbolos en el visitor para almacenar valores de variables.
4. **Pruebas**:
   - Crea un conjunto de archivos de prueba (`Prueba2.expr`, `Prueba3.expr`) con casos extremos (e.g., `sin(0)`, `factorial(-1)`) para validar robustez.
5. **Optimización**:
   - Optimiza el visitor para evitar cálculos redundantes (e.g., almacenar resultados intermedios en expresiones repetidas).
6. **Interfaz Gráfica**:
   - Integra una interfaz simple con Java Swing o Tkinter (Python) para ingresar expresiones interactivamente.
