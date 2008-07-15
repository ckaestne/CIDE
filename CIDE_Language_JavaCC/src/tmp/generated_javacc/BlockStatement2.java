package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BlockStatement2 extends BlockStatement {
  public BlockStatement2(Statement statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public BlockStatement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BlockStatement2(cloneProperties(),firstToken,lastToken);
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
}
