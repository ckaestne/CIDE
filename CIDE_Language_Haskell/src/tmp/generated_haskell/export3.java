package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class export3 extends export {
  public export3(naam naam1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naam>("naam1", naam1)
    }, firstToken, lastToken);
  }
  public export3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new export3(cloneProperties(),firstToken,lastToken);
  }
  public naam getNaam1() {
    return ((PropertyOne<naam>)getProperty("naam1")).getValue();
  }
}
