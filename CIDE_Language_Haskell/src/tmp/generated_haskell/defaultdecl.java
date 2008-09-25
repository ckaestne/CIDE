package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class defaultdecl extends definition {
  public defaultdecl(functiontypeList functiontypeList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<functiontypeList>("functiontypeList", functiontypeList)
    }, firstToken, lastToken);
  }
  public defaultdecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new defaultdecl(cloneProperties(),firstToken,lastToken);
  }
  public functiontypeList getFunctiontypeList() {
    return ((PropertyOne<functiontypeList>)getProperty("functiontypeList")).getValue();
  }
}
