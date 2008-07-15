package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

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
