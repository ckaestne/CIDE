package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement16 extends Statement {
  public Statement16(IASTNode tryStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<IASTNode,Statement>("tryStatement", tryStatement, "block")
    }, firstToken, lastToken);
  }
  public Statement16(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement16(cloneProperties(),firstToken,lastToken);
  }
  public IASTNode getTryStatement() {
    return ((PropertyWrapper<IASTNode,Statement>)getProperty("tryStatement")).getValue();
  }
}
