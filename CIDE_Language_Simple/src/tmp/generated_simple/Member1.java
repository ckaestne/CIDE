package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Member1 extends Member {
  public Member1(Method method, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Method>("method", method)
    }, firstToken, lastToken);
  }
  public Member1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Member1(cloneProperties(),firstToken,lastToken);
  }
  public Method getMethod() {
    return ((PropertyOne<Method>)getProperty("method")).getValue();
  }
}
