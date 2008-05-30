package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement9 extends Statement {
  public Statement9(ASTNode doStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<ASTNode,Statement>("doStatement", doStatement, "statement")
    }, firstToken, lastToken);
  }
  public Statement9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Statement9(cloneProperties(),firstToken,lastToken);
  }
  public ASTNode getDoStatement() {
    return ((PropertyWrapper<ASTNode,Statement>)getProperty("doStatement")).getValue();
  }
}
