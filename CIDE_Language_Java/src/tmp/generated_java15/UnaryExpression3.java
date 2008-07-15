package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpression3 extends UnaryExpression {
  public UnaryExpression3(PreDecrementExpression preDecrementExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PreDecrementExpression>("preDecrementExpression", preDecrementExpression)
    }, firstToken, lastToken);
  }
  public UnaryExpression3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpression3(cloneProperties(),firstToken,lastToken);
  }
  public PreDecrementExpression getPreDecrementExpression() {
    return ((PropertyOne<PreDecrementExpression>)getProperty("preDecrementExpression")).getValue();
  }
}
