package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class inst4 extends inst {
  public inst4(simpletype simpletype, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<simpletype>("simpletype", simpletype)
    }, firstToken, lastToken);
  }
  public inst4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new inst4(cloneProperties(),firstToken,lastToken);
  }
  public simpletype getSimpletype() {
    return ((PropertyZeroOrOne<simpletype>)getProperty("simpletype")).getValue();
  }
}
