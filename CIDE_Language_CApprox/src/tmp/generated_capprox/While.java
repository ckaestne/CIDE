package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class While extends CodeUnit_InBlock {
  public While(WhileStatement whileStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<WhileStatement>("whileStatement", whileStatement)
    }, firstToken, lastToken);
  }
  public While(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new While(cloneProperties(),firstToken,lastToken);
  }
  public WhileStatement getWhileStatement() {
    return ((PropertyOne<WhileStatement>)getProperty("whileStatement")).getValue();
  }
}
