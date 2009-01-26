package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expr extends GenASTNode {
  public expr(xor_expr xor_expr, ArrayList<xor_expr> xor_expr1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<xor_expr>("xor_expr", xor_expr),
      new PropertyZeroOrMore<xor_expr>("xor_expr1", xor_expr1)
    }, firstToken, lastToken);
  }
  public expr(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr(cloneProperties(),firstToken,lastToken);
  }
  public xor_expr getXor_expr() {
    return ((PropertyOne<xor_expr>)getProperty("xor_expr")).getValue();
  }
  public ArrayList<xor_expr> getXor_expr1() {
    return ((PropertyZeroOrMore<xor_expr>)getProperty("xor_expr1")).getValue();
  }
}
