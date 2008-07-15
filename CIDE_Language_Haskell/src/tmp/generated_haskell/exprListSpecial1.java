package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprListSpecial1 extends exprListSpecial {
  public exprListSpecial1(expr expr, altSpecialList altSpecialList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expr>("expr", expr),
      new PropertyOne<altSpecialList>("altSpecialList", altSpecialList)
    }, firstToken, lastToken);
  }
  public exprListSpecial1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprListSpecial1(cloneProperties(),firstToken,lastToken);
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
  public altSpecialList getAltSpecialList() {
    return ((PropertyOne<altSpecialList>)getProperty("altSpecialList")).getValue();
  }
}
