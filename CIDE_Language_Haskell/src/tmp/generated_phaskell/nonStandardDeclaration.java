package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class nonStandardDeclaration extends decl {
  public nonStandardDeclaration(nonstddecl nonstddecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<nonstddecl>("nonstddecl", nonstddecl)
    }, firstToken, lastToken);
  }
  public nonStandardDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new nonStandardDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public nonstddecl getNonstddecl() {
    return ((PropertyOne<nonstddecl>)getProperty("nonstddecl")).getValue();
  }
}
