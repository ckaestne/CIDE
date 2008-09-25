package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class typeSignature extends decl {
  public typeSignature(signdecl signdecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<signdecl>("signdecl", signdecl)
    }, firstToken, lastToken);
  }
  public typeSignature(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new typeSignature(cloneProperties(),firstToken,lastToken);
  }
  public signdecl getSigndecl() {
    return ((PropertyOne<signdecl>)getProperty("signdecl")).getValue();
  }
}
