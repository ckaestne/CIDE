package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Literal6 extends Literal {
  public Literal6(ASTStringNode regular_expression_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("regular_expression_literal", regular_expression_literal)
    }, firstToken, lastToken);
  }
  public Literal6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Literal6(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getRegular_expression_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("regular_expression_literal")).getValue();
  }
}
