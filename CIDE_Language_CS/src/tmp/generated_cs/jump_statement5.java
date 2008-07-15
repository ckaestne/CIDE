package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class jump_statement5 extends jump_statement {
  public jump_statement5(throw_statement throw_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<throw_statement>("throw_statement", throw_statement)
    }, firstToken, lastToken);
  }
  public jump_statement5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new jump_statement5(cloneProperties(),firstToken,lastToken);
  }
  public throw_statement getThrow_statement() {
    return ((PropertyOne<throw_statement>)getProperty("throw_statement")).getValue();
  }
}
