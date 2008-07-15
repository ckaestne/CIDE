package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryExpression2 extends PrimaryExpression {
  public PrimaryExpression2(MethodInvoke methodInvoke, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MethodInvoke>("methodInvoke", methodInvoke)
    }, firstToken, lastToken);
  }
  public PrimaryExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryExpression2(cloneProperties(),firstToken,lastToken);
  }
  public MethodInvoke getMethodInvoke() {
    return ((PropertyOne<MethodInvoke>)getProperty("methodInvoke")).getValue();
  }
}
