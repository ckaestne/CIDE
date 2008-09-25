package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class inst1 extends inst {
  public inst1(gtycon gtycon, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<gtycon>("gtycon", gtycon)
    }, firstToken, lastToken);
  }
  public inst1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new inst1(cloneProperties(),firstToken,lastToken);
  }
  public gtycon getGtycon() {
    return ((PropertyOne<gtycon>)getProperty("gtycon")).getValue();
  }
}
