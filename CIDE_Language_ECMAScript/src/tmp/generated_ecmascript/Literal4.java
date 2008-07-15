package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal4 extends Literal {
  public Literal4(ASTStringNode boolean_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("boolean_literal", boolean_literal)
    }, firstToken, lastToken);
  }
  public Literal4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getBoolean_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("boolean_literal")).getValue();
  }
}
