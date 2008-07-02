package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expressie8 extends expressie {
  public expressie8(exprListSpecial exprListSpecial, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<exprListSpecial>("exprListSpecial", exprListSpecial)
    }, firstToken, lastToken);
  }
  public expressie8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expressie8(cloneProperties(),firstToken,lastToken);
  }
  public exprListSpecial getExprListSpecial() {
    return ((PropertyZeroOrOne<exprListSpecial>)getProperty("exprListSpecial")).getValue();
  }
}
