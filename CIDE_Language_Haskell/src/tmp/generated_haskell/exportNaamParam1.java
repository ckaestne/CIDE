package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gparser.Token;

public class exportNaamParam1 extends exportNaamParam {
  public exportNaamParam1(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public exportNaamParam1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exportNaamParam1(cloneProperties(),firstToken,lastToken);
  }
}
