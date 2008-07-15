package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement11 extends Statement {
  public Statement11(BreakStatement breakStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BreakStatement>("breakStatement", breakStatement)
    }, firstToken, lastToken);
  }
  public Statement11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement11(cloneProperties(),firstToken,lastToken);
  }
  public BreakStatement getBreakStatement() {
    return ((PropertyOne<BreakStatement>)getProperty("breakStatement")).getValue();
  }
}
