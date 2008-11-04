package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class patroonMain3 extends patroonMain {
  public patroonMain3(var var, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var)
    }, firstToken, lastToken);
  }
  public patroonMain3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patroonMain3(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
}
