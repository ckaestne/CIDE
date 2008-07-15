package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpression3 extends UnaryExpression {
  public UnaryExpression3(UnaryExpression unaryExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<UnaryExpression>("unaryExpression1", unaryExpression1)
    }, firstToken, lastToken);
  }
  public UnaryExpression3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpression3(cloneProperties(),firstToken,lastToken);
  }
  public UnaryExpression getUnaryExpression1() {
    return ((PropertyOne<UnaryExpression>)getProperty("unaryExpression1")).getValue();
  }
}
