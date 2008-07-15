package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class unary_expression1 extends unary_expression {
  public unary_expression1(unary_operator unary_operator, unary_expression unary_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<unary_operator>("unary_operator", unary_operator),
      new PropertyOne<unary_expression>("unary_expression", unary_expression)
    }, firstToken, lastToken);
  }
  public unary_expression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new unary_expression1(cloneProperties(),firstToken,lastToken);
  }
  public unary_operator getUnary_operator() {
    return ((PropertyOne<unary_operator>)getProperty("unary_operator")).getValue();
  }
  public unary_expression getUnary_expression() {
    return ((PropertyOne<unary_expression>)getProperty("unary_expression")).getValue();
  }
}
