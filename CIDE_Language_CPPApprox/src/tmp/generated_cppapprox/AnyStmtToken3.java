package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyStmtToken3 extends AnyStmtToken {
  public AnyStmtToken3(ASTStringNode other, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("other", other)
    }, firstToken, lastToken);
  }
  public AnyStmtToken3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyStmtToken3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getOther() {
    return ((PropertyOne<ASTStringNode>)getProperty("other")).getValue();
  }
}
