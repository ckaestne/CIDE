package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement14 extends Statement {
  public Statement14(SwitchStatement switchStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SwitchStatement>("switchStatement", switchStatement)
    }, firstToken, lastToken);
  }
  public Statement14(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement14(cloneProperties(),firstToken,lastToken);
  }
  public SwitchStatement getSwitchStatement() {
    return ((PropertyOne<SwitchStatement>)getProperty("switchStatement")).getValue();
  }
}
