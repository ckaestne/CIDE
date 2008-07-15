package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyTypeDefToken3 extends AnyTypeDefToken {
  public AnyTypeDefToken3(AnyStmtToken anyStmtToken, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AnyStmtToken>("anyStmtToken", anyStmtToken)
    }, firstToken, lastToken);
  }
  public AnyTypeDefToken3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyTypeDefToken3(cloneProperties(),firstToken,lastToken);
  }
  public AnyStmtToken getAnyStmtToken() {
    return ((PropertyOne<AnyStmtToken>)getProperty("anyStmtToken")).getValue();
  }
}
