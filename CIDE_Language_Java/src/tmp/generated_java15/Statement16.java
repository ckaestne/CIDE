package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement16 extends Statement {
  public Statement16(ASTNode tryStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<ASTNode,Statement>("tryStatement", tryStatement, "block")
    }, firstToken, lastToken);
  }
  public Statement16(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Statement16(cloneProperties(),firstToken,lastToken);
  }
  public ASTNode getTryStatement() {
    return ((PropertyWrapper<ASTNode,Statement>)getProperty("tryStatement")).getValue();
  }
}
