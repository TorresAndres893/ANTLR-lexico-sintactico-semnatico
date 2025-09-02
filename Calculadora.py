import sys
from antlr4 import FileStream, CommonTokenStream
from Gramatica_CalLexer import Gramatica_CalLexer
from Gramatica_CalParser import Gramatica_CalParser
from CalVisitor import CalVisitorPython

def main(file):
    input_stream = FileStream(file)
    lexer = Gramatica_CalLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = Gramatica_CalParser(stream)
    tree = parser.prog()  # regla inicial

    visitor = CalVisitorPython()

    # recorrer cada stat en prog
    for stat in tree.stat():
        if hasattr(stat, 'expr') and stat.expr() is not None:
            result = visitor.visit(stat.expr())
            print(result)

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Uso: python CalculadoraPython.py archivo.expr")
        sys.exit(1)
    main(sys.argv[1])
