package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement7 extends Statement {
  public Statement7(ASTNode ifStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<ASTNode,Statement>("ifStatement", ifStatement, "statement")
    }, firstToken, lastToken);
  }
  public Statement7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Statement7(cloneProperties(),firstToken,lastToken);
  }
  public ASTNode getIfStatement() {
    return ((PropertyWrapper<ASTNode,Statement>)getProperty("ifStatement")).getValue();
  }
}
