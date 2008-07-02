package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class patroonPrefix2 extends patroonPrefix {
  public patroonPrefix2(var var, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var)
    }, firstToken, lastToken);
  }
  public patroonPrefix2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patroonPrefix2(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
}
