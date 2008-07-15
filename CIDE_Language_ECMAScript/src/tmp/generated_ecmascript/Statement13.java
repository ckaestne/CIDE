package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement13 extends Statement {
  public Statement13(WithStatement withStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<WithStatement>("withStatement", withStatement)
    }, firstToken, lastToken);
  }
  public Statement13(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement13(cloneProperties(),firstToken,lastToken);
  }
  public WithStatement getWithStatement() {
    return ((PropertyOne<WithStatement>)getProperty("withStatement")).getValue();
  }
}
