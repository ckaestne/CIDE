package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal5 extends Literal {
  public Literal5(ASTStringNode null_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("null_literal", null_literal)
    }, firstToken, lastToken);
  }
  public Literal5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getNull_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("null_literal")).getValue();
  }
}
