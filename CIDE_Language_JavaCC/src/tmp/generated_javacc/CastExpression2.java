package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CastExpression2 extends CastExpression {
  public CastExpression2(Type type1, UnaryExpressionNotPlusMinus unaryExpressionNotPlusMinus, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Type>("type1", type1),
      new PropertyOne<UnaryExpressionNotPlusMinus>("unaryExpressionNotPlusMinus", unaryExpressionNotPlusMinus)
    }, firstToken, lastToken);
  }
  public CastExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CastExpression2(cloneProperties(),firstToken,lastToken);
  }
  public Type getType1() {
    return ((PropertyOne<Type>)getProperty("type1")).getValue();
  }
  public UnaryExpressionNotPlusMinus getUnaryExpressionNotPlusMinus() {
    return ((PropertyOne<UnaryExpressionNotPlusMinus>)getProperty("unaryExpressionNotPlusMinus")).getValue();
  }
}
