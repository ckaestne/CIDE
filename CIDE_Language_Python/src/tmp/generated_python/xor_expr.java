package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class xor_expr extends GenASTNode {
  public xor_expr(and_expr and_expr, ArrayList<and_expr> and_expr1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<and_expr>("and_expr", and_expr),
      new PropertyZeroOrMore<and_expr>("and_expr1", and_expr1)
    }, firstToken, lastToken);
  }
  public xor_expr(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new xor_expr(cloneProperties(),firstToken,lastToken);
  }
  public and_expr getAnd_expr() {
    return ((PropertyOne<and_expr>)getProperty("and_expr")).getValue();
  }
  public ArrayList<and_expr> getAnd_expr1() {
    return ((PropertyZeroOrMore<and_expr>)getProperty("and_expr1")).getValue();
  }
}
