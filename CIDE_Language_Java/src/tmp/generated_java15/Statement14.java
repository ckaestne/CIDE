package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement14 extends Statement {
  public Statement14(ThrowStatement throwStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ThrowStatement>("throwStatement", throwStatement)
    }, firstToken, lastToken);
  }
  public Statement14(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement14(cloneProperties(),firstToken,lastToken);
  }
  public ThrowStatement getThrowStatement() {
    return ((PropertyOne<ThrowStatement>)getProperty("throwStatement")).getValue();
  }
}
