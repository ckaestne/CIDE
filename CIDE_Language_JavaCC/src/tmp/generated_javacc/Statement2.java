package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement2 extends Statement {
  public Statement2(AssertStatement assertStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AssertStatement>("assertStatement", assertStatement)
    }, firstToken, lastToken);
  }
  public Statement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement2(cloneProperties(),firstToken,lastToken);
  }
  public AssertStatement getAssertStatement() {
    return ((PropertyOne<AssertStatement>)getProperty("assertStatement")).getValue();
  }
}
