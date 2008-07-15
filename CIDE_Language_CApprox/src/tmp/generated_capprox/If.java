package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class If extends CodeUnit_InBlock {
  public If(IfStatement ifStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<IfStatement>("ifStatement", ifStatement)
    }, firstToken, lastToken);
  }
  public If(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new If(cloneProperties(),firstToken,lastToken);
  }
  public IfStatement getIfStatement() {
    return ((PropertyOne<IfStatement>)getProperty("ifStatement")).getValue();
  }
}
