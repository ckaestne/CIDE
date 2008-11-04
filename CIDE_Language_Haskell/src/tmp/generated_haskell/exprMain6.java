package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprMain6 extends exprMain {
  public exprMain6(decls decls, expr expr6, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<decls>("decls", decls),
      new PropertyOne<expr>("expr6", expr6)
    }, firstToken, lastToken);
  }
  public exprMain6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprMain6(cloneProperties(),firstToken,lastToken);
  }
  public decls getDecls() {
    return ((PropertyOne<decls>)getProperty("decls")).getValue();
  }
  public expr getExpr6() {
    return ((PropertyOne<expr>)getProperty("expr6")).getValue();
  }
}
