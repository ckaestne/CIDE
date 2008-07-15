package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CastExpression1 extends CastExpression {
  public CastExpression1(Type type, UnaryExpression unaryExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Type>("type", type),
      new PropertyOne<UnaryExpression>("unaryExpression", unaryExpression)
    }, firstToken, lastToken);
  }
  public CastExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CastExpression1(cloneProperties(),firstToken,lastToken);
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
  public UnaryExpression getUnaryExpression() {
    return ((PropertyOne<UnaryExpression>)getProperty("unaryExpression")).getValue();
  }
}
