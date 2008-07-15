package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement16 extends Statement {
  public Statement16(TryStatement tryStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TryStatement>("tryStatement", tryStatement)
    }, firstToken, lastToken);
  }
  public Statement16(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement16(cloneProperties(),firstToken,lastToken);
  }
  public TryStatement getTryStatement() {
    return ((PropertyOne<TryStatement>)getProperty("tryStatement")).getValue();
  }
}
