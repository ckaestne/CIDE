package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class newlineOrStmt1 extends newlineOrStmt {
  public newlineOrStmt1(ASTStringNode newline, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("newline", newline)
    }, firstToken, lastToken);
  }
  public newlineOrStmt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new newlineOrStmt1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getNewline() {
    return ((PropertyOne<ASTStringNode>)getProperty("newline")).getValue();
  }
}
