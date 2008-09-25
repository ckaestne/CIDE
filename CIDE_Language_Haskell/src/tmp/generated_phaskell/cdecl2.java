package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class cdecl2 extends cdecl {
  public cdecl2(nonstddecl nonstddecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<nonstddecl>("nonstddecl", nonstddecl)
    }, firstToken, lastToken);
  }
  public cdecl2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new cdecl2(cloneProperties(),firstToken,lastToken);
  }
  public nonstddecl getNonstddecl() {
    return ((PropertyOne<nonstddecl>)getProperty("nonstddecl")).getValue();
  }
}
