package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class arith_exprEnd1 extends arith_exprEnd {
  public arith_exprEnd1(ASTStringNode plus, term term, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("plus", plus),
      new PropertyOne<term>("term", term)
    }, firstToken, lastToken);
  }
  public arith_exprEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new arith_exprEnd1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPlus() {
    return ((PropertyOne<ASTStringNode>)getProperty("plus")).getValue();
  }
  public term getTerm() {
    return ((PropertyOne<term>)getProperty("term")).getValue();
  }
}
