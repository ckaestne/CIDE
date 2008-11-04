package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expressie6 extends expressie {
  public expressie6(exprList exprList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<exprList>("exprList", exprList)
    }, firstToken, lastToken);
  }
  public expressie6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expressie6(cloneProperties(),firstToken,lastToken);
  }
  public exprList getExprList() {
    return ((PropertyZeroOrOne<exprList>)getProperty("exprList")).getValue();
  }
}
