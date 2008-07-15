package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprMain2 extends exprMain {
  public exprMain2(expr expr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr>("expr", expr)
    }, firstToken, lastToken);
  }
  public exprMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprMain2(cloneProperties(),firstToken,lastToken);
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
}
