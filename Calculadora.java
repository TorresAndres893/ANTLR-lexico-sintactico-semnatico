import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;

public class Calculadora {
    public static void main(String[] args) throws Exception {
        // Cargar archivo de entrada
        CharStream input = CharStreams.fromFileName(args[0]);

        // Crear lexer y parser
        Gramatica_CalLexer lexer = new Gramatica_CalLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Gramatica_CalParser parser = new Gramatica_CalParser(tokens);

        // Obtener árbol
        ParseTree tree = parser.prog();

        // Evaluar con visitor
        CalVisitor eval = new CalVisitor();
        eval.visit(tree);
    }
}
