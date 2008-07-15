package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class InvokeTarget2 extends InvokeTarget {
  public InvokeTarget2(NestedExpression nestedExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<NestedExpression>("nestedExpression", nestedExpression)
    }, firstToken, lastToken);
  }
  public InvokeTarget2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new InvokeTarget2(cloneProperties(),firstToken,lastToken);
  }
  public NestedExpression getNestedExpression() {
    return ((PropertyOne<NestedExpression>)getProperty("nestedExpression")).getValue();
  }
}
