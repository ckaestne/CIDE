package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class exprOperator extends GenASTNode {
  public exprOperator(operator operator, expr expr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<operator>("operator", operator),
      new PropertyOne<expr>("expr", expr)
    }, firstToken, lastToken);
  }
  public exprOperator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprOperator(cloneProperties(),firstToken,lastToken);
  }
  public operator getOperator() {
    return ((PropertyOne<operator>)getProperty("operator")).getValue();
  }
  public expr getExpr() {
    return ((PropertyOne<expr>)getProperty("expr")).getValue();
  }
}
