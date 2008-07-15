package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyStmtToken2 extends AnyStmtToken {
  public AnyStmtToken2(ASTStringNode literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("literal", literal)
    }, firstToken, lastToken);
  }
  public AnyStmtToken2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyStmtToken2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLiteral() {
    return ((PropertyOne<ASTStringNode>)getProperty("literal")).getValue();
  }
}
