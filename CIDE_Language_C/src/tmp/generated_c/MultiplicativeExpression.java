package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeExpression extends GenASTNode {
  public MultiplicativeExpression(CastExpression castExpression, MultiplicativeExpressionInt multiplicativeExpressionInt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CastExpression>("castExpression", castExpression),
      new PropertyZeroOrOne<MultiplicativeExpressionInt>("multiplicativeExpressionInt", multiplicativeExpressionInt)
    }, firstToken, lastToken);
  }
  public MultiplicativeExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultiplicativeExpression(cloneProperties(),firstToken,lastToken);
  }
  public CastExpression getCastExpression() {
    return ((PropertyOne<CastExpression>)getProperty("castExpression")).getValue();
  }
  public MultiplicativeExpressionInt getMultiplicativeExpressionInt() {
    return ((PropertyZeroOrOne<MultiplicativeExpressionInt>)getProperty("multiplicativeExpressionInt")).getValue();
  }
}
