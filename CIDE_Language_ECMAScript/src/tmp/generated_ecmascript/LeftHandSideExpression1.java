package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LeftHandSideExpression1 extends LeftHandSideExpression {
  public LeftHandSideExpression1(CallExpression callExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CallExpression>("callExpression", callExpression)
    }, firstToken, lastToken);
  }
  public LeftHandSideExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LeftHandSideExpression1(cloneProperties(),firstToken,lastToken);
  }
  public CallExpression getCallExpression() {
    return ((PropertyOne<CallExpression>)getProperty("callExpression")).getValue();
  }
}
