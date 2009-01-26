package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class arith_expr extends GenASTNode {
  public arith_expr(term term, ArrayList<arith_exprEnd> arith_exprEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<term>("term", term),
      new PropertyZeroOrMore<arith_exprEnd>("arith_exprEnd", arith_exprEnd)
    }, firstToken, lastToken);
  }
  public arith_expr(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new arith_expr(cloneProperties(),firstToken,lastToken);
  }
  public term getTerm() {
    return ((PropertyOne<term>)getProperty("term")).getValue();
  }
  public ArrayList<arith_exprEnd> getArith_exprEnd() {
    return ((PropertyZeroOrMore<arith_exprEnd>)getProperty("arith_exprEnd")).getValue();
  }
}
