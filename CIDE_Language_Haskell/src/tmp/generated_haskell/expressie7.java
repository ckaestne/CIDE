package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expressie7 extends expressie {
  public expressie7(exprListSpecial exprListSpecial, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<exprListSpecial>("exprListSpecial", exprListSpecial)
    }, firstToken, lastToken);
  }
  public expressie7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new expressie7(cloneProperties(),firstToken,lastToken);
  }
  public exprListSpecial getExprListSpecial() {
    return ((PropertyZeroOrOne<exprListSpecial>)getProperty("exprListSpecial")).getValue();
  }
}
