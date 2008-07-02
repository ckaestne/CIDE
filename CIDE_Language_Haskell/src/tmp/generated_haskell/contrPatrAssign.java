package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class contrPatrAssign extends GenASTNode {
  public contrPatrAssign(var var, patr patr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var),
      new PropertyOne<patr>("patr", patr)
    }, firstToken, lastToken);
  }
  public contrPatrAssign(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new contrPatrAssign(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
  public patr getPatr() {
    return ((PropertyOne<patr>)getProperty("patr")).getValue();
  }
}
