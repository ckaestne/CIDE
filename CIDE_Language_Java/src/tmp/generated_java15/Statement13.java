package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement13 extends Statement {
  public Statement13(ReturnStatement returnStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ReturnStatement>("returnStatement", returnStatement)
    }, firstToken, lastToken);
  }
  public Statement13(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement13(cloneProperties(),firstToken,lastToken);
  }
  public ReturnStatement getReturnStatement() {
    return ((PropertyOne<ReturnStatement>)getProperty("returnStatement")).getValue();
  }
}
