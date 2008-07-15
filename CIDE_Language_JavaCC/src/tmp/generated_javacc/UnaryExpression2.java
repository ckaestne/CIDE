package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpression2 extends UnaryExpression {
  public UnaryExpression2(PreIncrementExpression preIncrementExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PreIncrementExpression>("preIncrementExpression", preIncrementExpression)
    }, firstToken, lastToken);
  }
  public UnaryExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpression2(cloneProperties(),firstToken,lastToken);
  }
  public PreIncrementExpression getPreIncrementExpression() {
    return ((PropertyOne<PreIncrementExpression>)getProperty("preIncrementExpression")).getValue();
  }
}
