package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement10 extends Statement {
  public Statement10(BreakStatement breakStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BreakStatement>("breakStatement", breakStatement)
    }, firstToken, lastToken);
  }
  public Statement10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement10(cloneProperties(),firstToken,lastToken);
  }
  public BreakStatement getBreakStatement() {
    return ((PropertyOne<BreakStatement>)getProperty("breakStatement")).getValue();
  }
}
