package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class cdecls extends GenASTNode {
  public cdecls(cdeclsI cdeclsI, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<cdeclsI>("cdeclsI", cdeclsI)
    }, firstToken, lastToken);
  }
  public cdecls(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new cdecls(cloneProperties(),firstToken,lastToken);
  }
  public cdeclsI getCdeclsI() {
    return ((PropertyZeroOrOne<cdeclsI>)getProperty("cdeclsI")).getValue();
  }
}
