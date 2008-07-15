package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal2 extends Literal {
  public Literal2(ASTStringNode floating_point_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("floating_point_literal", floating_point_literal)
    }, firstToken, lastToken);
  }
  public Literal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFloating_point_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("floating_point_literal")).getValue();
  }
}
