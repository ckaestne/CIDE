package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprListSpecial3 extends exprListSpecial {
  public exprListSpecial3(ArrayList<expr> expr4, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<expr>("expr4", expr4)
    }, firstToken, lastToken);
  }
  public exprListSpecial3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprListSpecial3(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<expr> getExpr4() {
    return ((PropertyList<expr>)getProperty("expr4")).getValue();
  }
}
