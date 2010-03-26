package tmp;
class Num implements Exp {
   int value;
   Num(int val) { value =  val; }
   public int eval() { return value; }
   public String toString() { return ""+ value; }
}
