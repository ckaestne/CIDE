package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryExpression7 extends PrimaryExpression {
  public PrimaryExpression7(NestedExpression nestedExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<NestedExpression>("nestedExpression", nestedExpression)
    }, firstToken, lastToken);
  }
  public PrimaryExpression7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryExpression7(cloneProperties(),firstToken,lastToken);
  }
  public NestedExpression getNestedExpression() {
    return ((PropertyOne<NestedExpression>)getProperty("nestedExpression")).getValue();
  }
}
