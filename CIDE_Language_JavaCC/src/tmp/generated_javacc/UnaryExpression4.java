package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpression4 extends UnaryExpression {
  public UnaryExpression4(UnaryExpressionNotPlusMinus unaryExpressionNotPlusMinus, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<UnaryExpressionNotPlusMinus>("unaryExpressionNotPlusMinus", unaryExpressionNotPlusMinus)
    }, firstToken, lastToken);
  }
  public UnaryExpression4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpression4(cloneProperties(),firstToken,lastToken);
  }
  public UnaryExpressionNotPlusMinus getUnaryExpressionNotPlusMinus() {
    return ((PropertyOne<UnaryExpressionNotPlusMinus>)getProperty("unaryExpressionNotPlusMinus")).getValue();
  }
}
