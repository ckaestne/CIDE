package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class jump_statement4 extends jump_statement {
  public jump_statement4(return_statement return_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<return_statement>("return_statement", return_statement)
    }, firstToken, lastToken);
  }
  public jump_statement4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new jump_statement4(cloneProperties(),firstToken,lastToken);
  }
  public return_statement getReturn_statement() {
    return ((PropertyOne<return_statement>)getProperty("return_statement")).getValue();
  }
}
