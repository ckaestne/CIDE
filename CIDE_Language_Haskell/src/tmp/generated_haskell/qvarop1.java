package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class qvarop1 extends qvarop {
  public qvarop1(qvarid qvarid, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qvarid>("qvarid", qvarid)
    }, firstToken, lastToken);
  }
  public qvarop1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qvarop1(cloneProperties(),firstToken,lastToken);
  }
  public qvarid getQvarid() {
    return ((PropertyOne<qvarid>)getProperty("qvarid")).getValue();
  }
}
