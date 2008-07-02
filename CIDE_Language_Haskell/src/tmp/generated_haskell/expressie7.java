package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expressie7 extends expressie {
  public expressie7(exprList exprList1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<exprList>("exprList1", exprList1)
    }, firstToken, lastToken);
  }
  public expressie7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expressie7(cloneProperties(),firstToken,lastToken);
  }
  public exprList getExprList1() {
    return ((PropertyZeroOrOne<exprList>)getProperty("exprList1")).getValue();
  }
}
