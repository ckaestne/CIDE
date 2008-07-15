package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement3 extends Statement {
  public Statement3(CompoundStatement compoundStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CompoundStatement>("compoundStatement", compoundStatement)
    }, firstToken, lastToken);
  }
  public Statement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement3(cloneProperties(),firstToken,lastToken);
  }
  public CompoundStatement getCompoundStatement() {
    return ((PropertyOne<CompoundStatement>)getProperty("compoundStatement")).getValue();
  }
}
