package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class operator extends GenASTNode {
  public operator(qop qop, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qop>("qop", qop)
    }, firstToken, lastToken);
  }
  public operator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new operator(cloneProperties(),firstToken,lastToken);
  }
  public qop getQop() {
    return ((PropertyOne<qop>)getProperty("qop")).getValue();
  }
}
