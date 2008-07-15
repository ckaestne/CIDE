package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement7 extends Statement {
  public Statement7(IASTNode ifStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<IASTNode,Statement>("ifStatement", ifStatement, "statement")
    }, firstToken, lastToken);
  }
  public Statement7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement7(cloneProperties(),firstToken,lastToken);
  }
  public IASTNode getIfStatement() {
    return ((PropertyWrapper<IASTNode,Statement>)getProperty("ifStatement")).getValue();
  }
}
