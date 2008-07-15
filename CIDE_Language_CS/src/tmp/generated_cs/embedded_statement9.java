package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement9 extends embedded_statement {
  public embedded_statement9(lock_statement lock_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<lock_statement>("lock_statement", lock_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement9(cloneProperties(),firstToken,lastToken);
  }
  public lock_statement getLock_statement() {
    return ((PropertyOne<lock_statement>)getProperty("lock_statement")).getValue();
  }
}
