package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement9 extends Statement {
  public Statement9(ContinueStatement continueStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ContinueStatement>("continueStatement", continueStatement)
    }, firstToken, lastToken);
  }
  public Statement9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement9(cloneProperties(),firstToken,lastToken);
  }
  public ContinueStatement getContinueStatement() {
    return ((PropertyOne<ContinueStatement>)getProperty("continueStatement")).getValue();
  }
}
