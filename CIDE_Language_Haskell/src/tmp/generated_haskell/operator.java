package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

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
