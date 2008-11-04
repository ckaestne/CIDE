package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class terminal3 extends terminal {
  public terminal3(ASTStringNode string_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("string_literal", string_literal)
    }, firstToken, lastToken);
  }
  public terminal3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new terminal3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getString_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("string_literal")).getValue();
  }
}
