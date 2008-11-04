package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class imp1 extends imp {
  public imp1(var var, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var)
    }, firstToken, lastToken);
  }
  public imp1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new imp1(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
}
