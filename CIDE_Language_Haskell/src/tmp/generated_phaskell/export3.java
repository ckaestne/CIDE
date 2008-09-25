package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class export3 extends export {
  public export3(modid modid, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<modid>("modid", modid)
    }, firstToken, lastToken);
  }
  public export3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new export3(cloneProperties(),firstToken,lastToken);
  }
  public modid getModid() {
    return ((PropertyOne<modid>)getProperty("modid")).getValue();
  }
}
