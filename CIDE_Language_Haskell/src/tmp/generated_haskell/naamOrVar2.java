package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class naamOrVar2 extends naamOrVar {
  public naamOrVar2(var var, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var)
    }, firstToken, lastToken);
  }
  public naamOrVar2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new naamOrVar2(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
}
