package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal1 extends Literal {
  public Literal1(ASTStringNode integer_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("integer_literal", integer_literal)
    }, firstToken, lastToken);
  }
  public Literal1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInteger_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("integer_literal")).getValue();
  }
}
