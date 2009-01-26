package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class shift_expr extends GenASTNode {
  public shift_expr(arith_expr arith_expr, ArrayList<shift_exprEnd> shift_exprEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<arith_expr>("arith_expr", arith_expr),
      new PropertyZeroOrMore<shift_exprEnd>("shift_exprEnd", shift_exprEnd)
    }, firstToken, lastToken);
  }
  public shift_expr(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new shift_expr(cloneProperties(),firstToken,lastToken);
  }
  public arith_expr getArith_expr() {
    return ((PropertyOne<arith_expr>)getProperty("arith_expr")).getValue();
  }
  public ArrayList<shift_exprEnd> getShift_exprEnd() {
    return ((PropertyZeroOrMore<shift_exprEnd>)getProperty("shift_exprEnd")).getValue();
  }
}
