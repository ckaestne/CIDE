package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class newtypeParam2 extends newtypeParam {
  public newtypeParam2(type type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type)
    }, firstToken, lastToken);
  }
  public newtypeParam2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new newtypeParam2(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
}
