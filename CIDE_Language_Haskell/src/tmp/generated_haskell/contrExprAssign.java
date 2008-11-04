package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class contrExprAssign extends GenASTNode {
  public contrExprAssign(var var, expr expr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var),
      new PropertyOne<expr>("expr", expr)
    }, firstToken, lastToken);
  }
  public contrExprAssign(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new contrExprAssign(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
}
