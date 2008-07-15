package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement3 extends Statement {
  public Statement3(VariableStatement variableStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<VariableStatement>("variableStatement", variableStatement)
    }, firstToken, lastToken);
  }
  public Statement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement3(cloneProperties(),firstToken,lastToken);
  }
  public VariableStatement getVariableStatement() {
    return ((PropertyOne<VariableStatement>)getProperty("variableStatement")).getValue();
  }
}
