package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement1 extends Statement {
  public Statement1(MethodInvocation methodInvocation, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MethodInvocation>("methodInvocation", methodInvocation)
    }, firstToken, lastToken);
  }
  public Statement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement1(cloneProperties(),firstToken,lastToken);
  }
  public MethodInvocation getMethodInvocation() {
    return ((PropertyOne<MethodInvocation>)getProperty("methodInvocation")).getValue();
  }
}
