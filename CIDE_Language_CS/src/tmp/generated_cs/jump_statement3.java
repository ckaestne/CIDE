package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class jump_statement3 extends jump_statement {
  public jump_statement3(goto_statement goto_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<goto_statement>("goto_statement", goto_statement)
    }, firstToken, lastToken);
  }
  public jump_statement3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new jump_statement3(cloneProperties(),firstToken,lastToken);
  }
  public goto_statement getGoto_statement() {
    return ((PropertyOne<goto_statement>)getProperty("goto_statement")).getValue();
  }
}
