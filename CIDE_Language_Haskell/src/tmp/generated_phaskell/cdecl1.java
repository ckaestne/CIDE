package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class cdecl1 extends cdecl {
  public cdecl1(signdecl signdecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<signdecl>("signdecl", signdecl)
    }, firstToken, lastToken);
  }
  public cdecl1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new cdecl1(cloneProperties(),firstToken,lastToken);
  }
  public signdecl getSigndecl() {
    return ((PropertyOne<signdecl>)getProperty("signdecl")).getValue();
  }
}
