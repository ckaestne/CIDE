package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class defaultdecl extends topdecl {
  public defaultdecl(list list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<list>("list", list)
    }, firstToken, lastToken);
  }
  public defaultdecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new defaultdecl(cloneProperties(),firstToken,lastToken);
  }
  public list getList() {
    return ((PropertyOne<list>)getProperty("list")).getValue();
  }
}
