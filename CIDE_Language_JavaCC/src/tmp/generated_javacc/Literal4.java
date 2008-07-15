package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal4 extends Literal {
  public Literal4(ASTStringNode string_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("string_literal", string_literal)
    }, firstToken, lastToken);
  }
  public Literal4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getString_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("string_literal")).getValue();
  }
}
