package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class details2 extends details {
  public details2(cnamelist cnamelist, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<cnamelist>("cnamelist", cnamelist)
    }, firstToken, lastToken);
  }
  public details2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new details2(cloneProperties(),firstToken,lastToken);
  }
  public cnamelist getCnamelist() {
    return ((PropertyZeroOrOne<cnamelist>)getProperty("cnamelist")).getValue();
  }
}
