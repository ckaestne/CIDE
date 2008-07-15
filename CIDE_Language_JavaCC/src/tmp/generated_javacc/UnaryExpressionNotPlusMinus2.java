package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpressionNotPlusMinus2 extends UnaryExpressionNotPlusMinus {
  public UnaryExpressionNotPlusMinus2(CastExpression castExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CastExpression>("castExpression", castExpression)
    }, firstToken, lastToken);
  }
  public UnaryExpressionNotPlusMinus2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpressionNotPlusMinus2(cloneProperties(),firstToken,lastToken);
  }
  public CastExpression getCastExpression() {
    return ((PropertyOne<CastExpression>)getProperty("castExpression")).getValue();
  }
}
