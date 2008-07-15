package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement extends GenASTNode {
  public Statement(ArrayList<AnyStmtToken> anyStmtToken, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<AnyStmtToken>("anyStmtToken", anyStmtToken)
    }, firstToken, lastToken);
  }
  public Statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<AnyStmtToken> getAnyStmtToken() {
    return ((PropertyZeroOrMore<AnyStmtToken>)getProperty("anyStmtToken")).getValue();
  }
}
