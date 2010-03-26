package tmp;
class Plus implements Exp {
   Exp x;
   Exp y;
   Plus( Exp x, Exp y ) { this.x = x; this.y = y; }
   public int eval() { return x.eval() + y.eval(); }
   public String toString() { return x + " + " + y ; }
}