package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class arith_exprEnd2 extends arith_exprEnd {
  public arith_exprEnd2(ASTStringNode minus, term term1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("minus", minus),
      new PropertyOne<term>("term1", term1)
    }, firstToken, lastToken);
  }
  public arith_exprEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new arith_exprEnd2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getMinus() {
    return ((PropertyOne<ASTStringNode>)getProperty("minus")).getValue();
  }
  public term getTerm1() {
    return ((PropertyOne<term>)getProperty("term1")).getValue();
  }
}
