package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyOneOrMore;
import cide.gparser.Token;

public class exprMain3 extends exprMain {
  public exprMain3(ArrayList<patroon> patroon, expr expr1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<patroon>("patroon", patroon),
      new PropertyOne<expr>("expr1", expr1)
    }, firstToken, lastToken);
  }
  public exprMain3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprMain3(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<patroon> getPatroon() {
    return ((PropertyOneOrMore<patroon>)getProperty("patroon")).getValue();
  }
  public expr getExpr1() {
    return ((PropertyOne<expr>)getProperty("expr1")).getValue();
  }
}
