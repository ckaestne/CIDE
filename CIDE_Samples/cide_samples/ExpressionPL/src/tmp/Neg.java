package tmp;
class Neg implements Exp {
   Exp x;
   Neg( Exp x ) { this.x = x; }
   public int eval() { return - x.eval(); }
   public String toString() {  return  " -(" + x + ") "; }
}  