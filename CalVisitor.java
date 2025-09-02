import java.util.HashMap;
import java.util.Map;

public class CalVisitor extends Gramatica_CalBaseVisitor<Double> {
    private Map<String, Double> memory = new HashMap<>();
    private boolean grados = true; // true = grados, false = radianes

	
    
    /** Multiplicación / División */
    @Override
    public Double visitMulDiv(Gramatica_CalParser.MulDivContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        if (ctx.op.getType() == Gramatica_CalParser.MUL) return left * right;
        return left / right;
    }
    
    /** Imprimir: expr NEWLINE */
    @Override
    public Double visitPrintExpr(Gramatica_CalParser.PrintExprContext ctx) {
        Double value = visit(ctx.expr());
        System.out.println(value);
        return 0.0;
    }

    /** Entero */
    @Override
    public Double visitInt(Gramatica_CalParser.IntContext ctx) {
        return Double.valueOf(ctx.INT().getText());
    }

    /** Decimal */
    @Override
    public Double visitDouble(Gramatica_CalParser.DoubleContext ctx) {
        return Double.valueOf(ctx.DOUBLE().getText());
    }

    /** Paréntesis */
    @Override
    public Double visitParens(Gramatica_CalParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    /** Suma / Resta */
    @Override
    public Double visitAddSub(Gramatica_CalParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        if (ctx.op.getType() == Gramatica_CalParser.ADD) return left + right;
        return left - right;
    }

    @Override
public Double visitFactorialExpr(Gramatica_CalParser.FactorialExprContext ctx) {
    double value = visit(ctx.atom());
    return (double) factorial((int) value);
}

private double factorial(int n) {
    if (n < 0) throw new RuntimeException("Factorial no definido para negativos");
    double result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}


    /** sin(expr) */
    @Override
    public Double visitSinExpr(Gramatica_CalParser.SinExprContext ctx) {
        double value = visit(ctx.expr());
        if (grados) value = Math.toRadians(value);
        return Math.sin(value);
    }

    /** cos(expr) */
    @Override
    public Double visitCosExpr(Gramatica_CalParser.CosExprContext ctx) {
        double value = visit(ctx.expr());
        if (grados) value = Math.toRadians(value);
        return Math.cos(value);
    }

    /** tan(expr) */
    @Override
    public Double visitTanExpr(Gramatica_CalParser.TanExprContext ctx) {
        double value = visit(ctx.expr());
        if (grados) value = Math.toRadians(value);
        return Math.tan(value);
    }

    /** sqrt(expr) */
    @Override
    public Double visitSqrtExpr(Gramatica_CalParser.SqrtExprContext ctx) {
        double value = visit(ctx.expr());
        return Math.sqrt(value);
    }

    /** ln(expr) */
    @Override
    public Double visitLnExpr(Gramatica_CalParser.LnExprContext ctx) {
        double value = visit(ctx.expr());
        return Math.log(value);
    }

    /** log(expr) */
    @Override
    public Double visitLogExpr(Gramatica_CalParser.LogExprContext ctx) {
        double value = visit(ctx.expr());
        return Math.log10(value);
    }

    /** Alternar grados/radianes */
    public void setgrados(boolean grados) {
        this.grados = grados;
    }
}
