package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SourceElement2 extends SourceElement {
  public SourceElement2(Statement statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public SourceElement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SourceElement2(cloneProperties(),firstToken,lastToken);
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
}
