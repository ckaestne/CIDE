package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Switch extends CodeUnit_InBlock {
  public Switch(SwitchStatement switchStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SwitchStatement>("switchStatement", switchStatement)
    }, firstToken, lastToken);
  }
  public Switch(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Switch(cloneProperties(),firstToken,lastToken);
  }
  public SwitchStatement getSwitchStatement() {
    return ((PropertyOne<SwitchStatement>)getProperty("switchStatement")).getValue();
  }
}
