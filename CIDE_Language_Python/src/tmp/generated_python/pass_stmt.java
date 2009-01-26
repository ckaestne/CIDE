package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class pass_stmt extends GenASTNode {
  public pass_stmt(ASTStringNode pass, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("pass", pass)
    }, firstToken, lastToken);
  }
  public pass_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new pass_stmt(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPass() {
    return ((PropertyOne<ASTStringNode>)getProperty("pass")).getValue();
  }
}
