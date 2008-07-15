package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement12 extends Statement {
  public Statement12(ContinueStatement continueStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ContinueStatement>("continueStatement", continueStatement)
    }, firstToken, lastToken);
  }
  public Statement12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement12(cloneProperties(),firstToken,lastToken);
  }
  public ContinueStatement getContinueStatement() {
    return ((PropertyOne<ContinueStatement>)getProperty("continueStatement")).getValue();
  }
}
