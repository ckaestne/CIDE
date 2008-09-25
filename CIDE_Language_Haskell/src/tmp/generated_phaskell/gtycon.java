package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class gtycon extends GenASTNode {
  public gtycon(qtyconorcls qtyconorcls, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qtyconorcls>("qtyconorcls", qtyconorcls)
    }, firstToken, lastToken);
  }
  public gtycon(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new gtycon(cloneProperties(),firstToken,lastToken);
  }
  public qtyconorcls getQtyconorcls() {
    return ((PropertyOne<qtyconorcls>)getProperty("qtyconorcls")).getValue();
  }
}
