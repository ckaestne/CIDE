package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExplicitConstructorInvocation1 extends ExplicitConstructorInvocation {
  public ExplicitConstructorInvocation1(Arguments arguments, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Arguments>("arguments", arguments)
    }, firstToken, lastToken);
  }
  public ExplicitConstructorInvocation1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExplicitConstructorInvocation1(cloneProperties(),firstToken,lastToken);
  }
  public Arguments getArguments() {
    return ((PropertyOne<Arguments>)getProperty("arguments")).getValue();
  }
}
