package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement12 extends Statement {
  public Statement12(ReturnStatement returnStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ReturnStatement>("returnStatement", returnStatement)
    }, firstToken, lastToken);
  }
  public Statement12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement12(cloneProperties(),firstToken,lastToken);
  }
  public ReturnStatement getReturnStatement() {
    return ((PropertyOne<ReturnStatement>)getProperty("returnStatement")).getValue();
  }
}
