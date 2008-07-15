package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpression4 extends UnaryExpression {
  public UnaryExpression4(UnaryOperator unaryOperator, CastExpression castExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<UnaryOperator>("unaryOperator", unaryOperator),
      new PropertyOne<CastExpression>("castExpression", castExpression)
    }, firstToken, lastToken);
  }
  public UnaryExpression4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpression4(cloneProperties(),firstToken,lastToken);
  }
  public UnaryOperator getUnaryOperator() {
    return ((PropertyOne<UnaryOperator>)getProperty("unaryOperator")).getValue();
  }
  public CastExpression getCastExpression() {
    return ((PropertyOne<CastExpression>)getProperty("castExpression")).getValue();
  }
}
