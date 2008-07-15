package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyStmtToken4 extends AnyStmtToken {
  public AnyStmtToken4(ASTStringNode symbols, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("symbols", symbols)
    }, firstToken, lastToken);
  }
  public AnyStmtToken4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyStmtToken4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSymbols() {
    return ((PropertyOne<ASTStringNode>)getProperty("symbols")).getValue();
  }
}
