# ENTREGA PROYECTO JUEVES 11, DIAS MAXIMOS REALMENTE PRACTICOS EN EJECUICON 7, PARCIAL ES EL MARTES SIGUIENTE, (EJEMPLO PROGRAMA AWK EN BASE DE DIAGRAMA , LAMBDA GRAMATICA REGULR EN LEX, PROGRAMA EN C QUE ENCUENTRE PALABRAS, COMPARAACION DE LENGUJAE COMPILADO E INTERPRETADO, USANDO ANTLR CALCULADORA CON NUMEROS COMPLEJOS(FACTORIALES),  IA=0 ,   )

# ANTLR-lexico-sintactico-semnatico

## Instalación y Uso de ANTLR en Linux con Visitor para Evaluar Expresiones

Este documento explica cómo instalar ANTLR en Linux, configurar el entorno, crear una gramática para expresiones aritméticas, generar el parser y lexer, implementar un visitor para evaluar expresiones y ejecutar el programa en Java.

---

### Requisitos Previos

- Java JDK 8 o superior instalado
- `curl` o `wget` para descargar archivos
- Terminal de Linux

---

### 1. Descargar ANTLR

Descarga el archivo `.jar` de ANTLR:

```bash
curl -O https://www.antlr.org/download/antlr-4.13.1-complete.jar
```
 Posterior a esto es recomendable movilizar el archivo .jar, a una ubicacion a gusto para mantener organizacion.
 
### 2. Configurar Variables de Entorno
Dónde está el archivo `.jar` de ANTLR 
```bash 
export CLASSPATH=".:/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH"
```
Definir un alias para que puedas usar comandos simples (`antlr4` y `grun`) sin escribir todo el comando Java completo.
```bash
alias antlr4='java -Xmx500M -cp "/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
```
```bash
alias grun='java -Xmx500M -cp "/home/ATLER/Desktop/antlr-4.13.1-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'
```
Aplicacion de estos cambios
```bash
source ~/.bashrc
```
## Objetivo

 Construccion de una calculadora usando la gramtica LabelEcpr.g4 usando Eval visitor.java
    La calculadora es capaz de realizar cos sin tan, 
    raiz ciadrada usando sqrt
    logaritmo natural, logaritmo base 10
    usa grados y radianes
    Calculo factorial (!)

## Analisis Lexico

  Escaner, token lexer

    Lexema la cadena de caracteres
    token entero, lexema 6 Fila columna
    cadena que no coincide con ninguno de los patrones de los tokens posibles

    Acciones



     Palabras Reservadas () estrucutura de datos donde se encuentra las palabras reservadas ejemplo del semstre separado(for, while, in, range)
      para las palabras reservadas creo que es mejor un diicioaario o un hash. 
    
     expresiones regulares 

     Considracion 

     princioio de la subcadena mas larga ejemplo (= es diferente de ==)

     
     
## Analisis   


## Automata de estaos finitos para el caso de (==), se debe realizar la quintupla de los AFD(estados, estado_inicial, estado_final, funcion_transicion_alfabeto). 
  NO SE PUEDE USAR ANTLR PARA AFD NI NINGUNA VARIACION, 

  concepto basico a usar flex, DIAGRAMAS 

expr op=('*'|'/') expr     #MulDiv  //Esta regla representa una expresión que realiza una operación de multiplicación (*) o división (/). Se denomina MulDiv

Ejecutaste antlr4 Gramatica_Cal.g4 y generó los archivos Gramatica_CalLexer.java, Gramatica_CalParser.java

Compilaste con javac Gramatica_Cal*.java sin errores.

Listaste los archivos .class generados.

6. Generar los archivos del parser y lexer  : antlr4 Gramatica_Cal.g4
7. Compilar los archivos Java generados   :  javac Gramatica_Cal*.java
8. Ejecutar Arbol sintactico grun Gramatica_Cal prog -gui Prueba.expr
9. falta la ejecucion dando la respuesta como lo hago

7. 
