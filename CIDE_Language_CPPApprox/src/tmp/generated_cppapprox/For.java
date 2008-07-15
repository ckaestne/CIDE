package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class For extends CodeUnit_InBlock {
  public For(ForStatement forStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ForStatement>("forStatement", forStatement)
    }, firstToken, lastToken);
  }
  public For(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new For(cloneProperties(),firstToken,lastToken);
  }
  public ForStatement getForStatement() {
    return ((PropertyOne<ForStatement>)getProperty("forStatement")).getValue();
  }
}
