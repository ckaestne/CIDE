package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement5 extends Statement {
  public Statement5(IterationStatement iterationStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<IterationStatement>("iterationStatement", iterationStatement)
    }, firstToken, lastToken);
  }
  public Statement5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement5(cloneProperties(),firstToken,lastToken);
  }
  public IterationStatement getIterationStatement() {
    return ((PropertyOne<IterationStatement>)getProperty("iterationStatement")).getValue();
  }
}
