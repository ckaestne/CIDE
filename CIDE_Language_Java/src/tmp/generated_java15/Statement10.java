package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement10 extends Statement {
  public Statement10(IASTNode forStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<IASTNode,Statement>("forStatement", forStatement, "statement")
    }, firstToken, lastToken);
  }
  public Statement10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement10(cloneProperties(),firstToken,lastToken);
  }
  public IASTNode getForStatement() {
    return ((PropertyWrapper<IASTNode,Statement>)getProperty("forStatement")).getValue();
  }
}
