package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement6 extends Statement {
  public Statement6(JumpStatement jumpStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JumpStatement>("jumpStatement", jumpStatement)
    }, firstToken, lastToken);
  }
  public Statement6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement6(cloneProperties(),firstToken,lastToken);
  }
  public JumpStatement getJumpStatement() {
    return ((PropertyOne<JumpStatement>)getProperty("jumpStatement")).getValue();
  }
}
