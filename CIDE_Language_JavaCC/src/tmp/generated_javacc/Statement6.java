package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement6 extends Statement {
  public Statement6(SwitchStatement switchStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SwitchStatement>("switchStatement", switchStatement)
    }, firstToken, lastToken);
  }
  public Statement6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement6(cloneProperties(),firstToken,lastToken);
  }
  public SwitchStatement getSwitchStatement() {
    return ((PropertyOne<SwitchStatement>)getProperty("switchStatement")).getValue();
  }
}
