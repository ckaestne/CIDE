package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class deriving1 extends deriving {
  public deriving1(naamList naamList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naamList>("naamList", naamList)
    }, firstToken, lastToken);
  }
  public deriving1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new deriving1(cloneProperties(),firstToken,lastToken);
  }
  public naamList getNaamList() {
    return ((PropertyOne<naamList>)getProperty("naamList")).getValue();
  }
}
