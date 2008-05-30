package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement15 extends Statement {
  public Statement15(ASTNode synchronizedStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<ASTNode,Statement>("synchronizedStatement", synchronizedStatement, "block")
    }, firstToken, lastToken);
  }
  public Statement15(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Statement15(cloneProperties(),firstToken,lastToken);
  }
  public ASTNode getSynchronizedStatement() {
    return ((PropertyWrapper<ASTNode,Statement>)getProperty("synchronizedStatement")).getValue();
  }
}
