package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrMore;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class expr extends GenASTNode {
  public expr(exprMain exprMain, ArrayList<exprOperator> exprOperator, exprEnd exprEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<exprMain>("exprMain", exprMain),
      new PropertyZeroOrMore<exprOperator>("exprOperator", exprOperator),
      new PropertyZeroOrOne<exprEnd>("exprEnd", exprEnd)
    }, firstToken, lastToken);
  }
  public expr(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expr(cloneProperties(),firstToken,lastToken);
  }
  public exprMain getExprMain() {
    return ((PropertyOne<exprMain>)getProperty("exprMain")).getValue();
  }
  public ArrayList<exprOperator> getExprOperator() {
    return ((PropertyZeroOrMore<exprOperator>)getProperty("exprOperator")).getValue();
  }
  public exprEnd getExprEnd() {
    return ((PropertyZeroOrOne<exprEnd>)getProperty("exprEnd")).getValue();
  }
}
