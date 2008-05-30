package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement8 extends Statement {
  public Statement8(ASTNode whileStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<ASTNode,Statement>("whileStatement", whileStatement, "statement")
    }, firstToken, lastToken);
  }
  public Statement8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Statement8(cloneProperties(),firstToken,lastToken);
  }
  public ASTNode getWhileStatement() {
    return ((PropertyWrapper<ASTNode,Statement>)getProperty("whileStatement")).getValue();
  }
}
