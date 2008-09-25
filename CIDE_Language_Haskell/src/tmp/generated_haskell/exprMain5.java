package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class exprMain5 extends exprMain {
  public exprMain5(ArrayList<caseInner> caseInner, expr expr5, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<caseInner>("caseInner", caseInner),
      new PropertyOne<expr>("expr5", expr5)
    }, firstToken, lastToken);
  }
  public exprMain5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprMain5(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<caseInner> getCaseInner() {
    return ((PropertyList<caseInner>)getProperty("caseInner")).getValue();
  }
  public expr getExpr5() {
    return ((PropertyOne<expr>)getProperty("expr5")).getValue();
  }
}
