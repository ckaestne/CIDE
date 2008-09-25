package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class inst3 extends inst {
  public inst3(conid conid, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<conid>("conid", conid)
    }, firstToken, lastToken);
  }
  public inst3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new inst3(cloneProperties(),firstToken,lastToken);
  }
  public conid getConid() {
    return ((PropertyOne<conid>)getProperty("conid")).getValue();
  }
}
