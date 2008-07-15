package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qop2 extends qop {
  public qop2(qconop qconop, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qconop>("qconop", qconop)
    }, firstToken, lastToken);
  }
  public qop2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qop2(cloneProperties(),firstToken,lastToken);
  }
  public qconop getQconop() {
    return ((PropertyOne<qconop>)getProperty("qconop")).getValue();
  }
}
