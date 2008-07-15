package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class embedded_statement5 extends embedded_statement {
  public embedded_statement5(jump_statement jump_statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<jump_statement>("jump_statement", jump_statement)
    }, firstToken, lastToken);
  }
  public embedded_statement5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new embedded_statement5(cloneProperties(),firstToken,lastToken);
  }
  public jump_statement getJump_statement() {
    return ((PropertyOne<jump_statement>)getProperty("jump_statement")).getValue();
  }
}
