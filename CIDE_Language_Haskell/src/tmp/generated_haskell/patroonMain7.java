package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class patroonMain7 extends patroonMain {
  public patroonMain7(patrList patrList2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<patrList>("patrList2", patrList2)
    }, firstToken, lastToken);
  }
  public patroonMain7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new patroonMain7(cloneProperties(),firstToken,lastToken);
  }
  public patrList getPatrList2() {
    return ((PropertyZeroOrOne<patrList>)getProperty("patrList2")).getValue();
  }
}
