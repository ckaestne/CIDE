package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement8 extends Statement {
  public Statement8(IASTNode whileStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<IASTNode,Statement>("whileStatement", whileStatement, "statement")
    }, firstToken, lastToken);
  }
  public Statement8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement8(cloneProperties(),firstToken,lastToken);
  }
  public IASTNode getWhileStatement() {
    return ((PropertyWrapper<IASTNode,Statement>)getProperty("whileStatement")).getValue();
  }
}
