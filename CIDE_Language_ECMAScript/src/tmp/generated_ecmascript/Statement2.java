package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement2 extends Statement {
  public Statement2(JScriptVarStatement jScriptVarStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JScriptVarStatement>("jScriptVarStatement", jScriptVarStatement)
    }, firstToken, lastToken);
  }
  public Statement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement2(cloneProperties(),firstToken,lastToken);
  }
  public JScriptVarStatement getJScriptVarStatement() {
    return ((PropertyOne<JScriptVarStatement>)getProperty("jScriptVarStatement")).getValue();
  }
}
