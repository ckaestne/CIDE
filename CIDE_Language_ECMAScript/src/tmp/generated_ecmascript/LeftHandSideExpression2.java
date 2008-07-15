package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LeftHandSideExpression2 extends LeftHandSideExpression {
  public LeftHandSideExpression2(MemberExpression memberExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberExpression>("memberExpression", memberExpression)
    }, firstToken, lastToken);
  }
  public LeftHandSideExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LeftHandSideExpression2(cloneProperties(),firstToken,lastToken);
  }
  public MemberExpression getMemberExpression() {
    return ((PropertyOne<MemberExpression>)getProperty("memberExpression")).getValue();
  }
}
