package tmp.generated_haskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyZeroOrOne;
import cide.gparser.Token;

public class patroonMain5 extends patroonMain {
  public patroonMain5(patrList patrList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<patrList>("patrList", patrList)
    }, firstToken, lastToken);
  }
  public patroonMain5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patroonMain5(cloneProperties(),firstToken,lastToken);
  }
  public patrList getPatrList() {
    return ((PropertyZeroOrOne<patrList>)getProperty("patrList")).getValue();
  }
}
