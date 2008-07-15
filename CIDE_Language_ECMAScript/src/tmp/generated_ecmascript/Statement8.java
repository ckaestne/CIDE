package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement8 extends Statement {
  public Statement8(IterationStatement iterationStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<IterationStatement>("iterationStatement", iterationStatement)
    }, firstToken, lastToken);
  }
  public Statement8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement8(cloneProperties(),firstToken,lastToken);
  }
  public IterationStatement getIterationStatement() {
    return ((PropertyOne<IterationStatement>)getProperty("iterationStatement")).getValue();
  }
}
