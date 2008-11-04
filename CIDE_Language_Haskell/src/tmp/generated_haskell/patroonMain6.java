package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class patroonMain6 extends patroonMain {
  public patroonMain6(patrList patrList1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<patrList>("patrList1", patrList1)
    }, firstToken, lastToken);
  }
  public patroonMain6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patroonMain6(cloneProperties(),firstToken,lastToken);
  }
  public patrList getPatrList1() {
    return ((PropertyZeroOrOne<patrList>)getProperty("patrList1")).getValue();
  }
}
