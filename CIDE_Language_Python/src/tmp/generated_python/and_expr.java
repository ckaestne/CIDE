package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class and_expr extends GenASTNode {
  public and_expr(shift_expr shift_expr, ArrayList<shift_expr> shift_expr1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<shift_expr>("shift_expr", shift_expr),
      new PropertyZeroOrMore<shift_expr>("shift_expr1", shift_expr1)
    }, firstToken, lastToken);
  }
  public and_expr(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new and_expr(cloneProperties(),firstToken,lastToken);
  }
  public shift_expr getShift_expr() {
    return ((PropertyOne<shift_expr>)getProperty("shift_expr")).getValue();
  }
  public ArrayList<shift_expr> getShift_expr1() {
    return ((PropertyZeroOrMore<shift_expr>)getProperty("shift_expr1")).getValue();
  }
}
