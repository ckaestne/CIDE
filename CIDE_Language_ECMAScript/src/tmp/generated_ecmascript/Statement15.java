package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement15 extends Statement {
  public Statement15(ThrowStatement throwStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ThrowStatement>("throwStatement", throwStatement)
    }, firstToken, lastToken);
  }
  public Statement15(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement15(cloneProperties(),firstToken,lastToken);
  }
  public ThrowStatement getThrowStatement() {
    return ((PropertyOne<ThrowStatement>)getProperty("throwStatement")).getValue();
  }
}
