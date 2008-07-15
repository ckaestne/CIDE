package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Stmt extends CodeUnit_InBlock {
  public Stmt(Statement statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public Stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Stmt(cloneProperties(),firstToken,lastToken);
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
}
