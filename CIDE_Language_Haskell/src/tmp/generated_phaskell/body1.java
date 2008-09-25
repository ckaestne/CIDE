package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class body1 extends body {
  public body1(impdecls impdecls, topdecls topdecls, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<impdecls>("impdecls", impdecls),
      new PropertyZeroOrOne<topdecls>("topdecls", topdecls)
    }, firstToken, lastToken);
  }
  public body1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new body1(cloneProperties(),firstToken,lastToken);
  }
  public impdecls getImpdecls() {
    return ((PropertyOne<impdecls>)getProperty("impdecls")).getValue();
  }
  public topdecls getTopdecls() {
    return ((PropertyZeroOrOne<topdecls>)getProperty("topdecls")).getValue();
  }
}
