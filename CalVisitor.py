from Gramatica_CalVisitor import Gramatica_CalVisitor
from Gramatica_CalParser import Gramatica_CalParser
import math

class CalVisitorPython(Gramatica_CalVisitor):
    # expr: MulDiv, AddSub, FactorialExpr, ToAtom
    def visitMulDiv(self, ctx: Gramatica_CalParser.MulDivContext):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == Gramatica_CalParser.MUL:
            return left * right
        else:
            return left / right

    def visitAddSub(self, ctx: Gramatica_CalParser.AddSubContext):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == Gramatica_CalParser.ADD:
            return left + right
        else:
            return left - right

    def visitFactorialExpr(self, ctx: Gramatica_CalParser.FactorialExprContext):
        n = int(self.visit(ctx.atom()))
        res = 1
        for i in range(1, n+1):
            res *= i
        return res

    def visitToAtom(self, ctx: Gramatica_CalParser.ToAtomContext):
        return self.visit(ctx.atom())

    # atom: UnaryMinus, Int, Double, Parens, SinExpr, CosExpr, TanExpr, SqrtExpr, LnExpr, LogExpr
    def visitUnaryMinus(self, ctx: Gramatica_CalParser.UnaryMinusContext):
        return -self.visit(ctx.atom())

    def visitInt(self, ctx: Gramatica_CalParser.IntContext):
        return int(ctx.INT().getText())

    def visitDouble(self, ctx: Gramatica_CalParser.DoubleContext):
        return float(ctx.DOUBLE().getText())

    def visitParens(self, ctx: Gramatica_CalParser.ParensContext):
        return self.visit(ctx.expr())

    def visitSinExpr(self, ctx: Gramatica_CalParser.SinExprContext):
        return math.sin(math.radians(self.visit(ctx.expr())))

    def visitCosExpr(self, ctx: Gramatica_CalParser.CosExprContext):
        return math.cos(math.radians(self.visit(ctx.expr())))

    def visitTanExpr(self, ctx: Gramatica_CalParser.TanExprContext):
        return math.tan(math.radians(self.visit(ctx.expr())))

    def visitSqrtExpr(self, ctx: Gramatica_CalParser.SqrtExprContext):
        return math.sqrt(self.visit(ctx.expr()))

    def visitLnExpr(self, ctx: Gramatica_CalParser.LnExprContext):
        return math.log(self.visit(ctx.expr()))

    def visitLogExpr(self, ctx: Gramatica_CalParser.LogExprContext):
        return math.log10(self.visit(ctx.expr()))
