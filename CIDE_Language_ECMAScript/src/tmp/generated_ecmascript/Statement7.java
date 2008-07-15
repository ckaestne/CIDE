package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement7 extends Statement {
  public Statement7(IfStatement ifStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<IfStatement>("ifStatement", ifStatement)
    }, firstToken, lastToken);
  }
  public Statement7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement7(cloneProperties(),firstToken,lastToken);
  }
  public IfStatement getIfStatement() {
    return ((PropertyOne<IfStatement>)getProperty("ifStatement")).getValue();
  }
}
