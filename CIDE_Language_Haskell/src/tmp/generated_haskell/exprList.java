package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprList extends GenASTNode {
  public exprList(ArrayList<expr> expr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<expr>("expr", expr)
    }, firstToken, lastToken);
  }
  public exprList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<expr> getExpr() {
    return ((PropertyList<expr>)getProperty("expr")).getValue();
  }
}
