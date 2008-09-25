package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

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
