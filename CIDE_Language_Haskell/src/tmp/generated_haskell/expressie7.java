package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

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
