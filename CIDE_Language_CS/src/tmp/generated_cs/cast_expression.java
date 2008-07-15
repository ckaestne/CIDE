package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class cast_expression extends GenASTNode {
  public cast_expression(type type, unary_expression unary_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyOne<unary_expression>("unary_expression", unary_expression)
    }, firstToken, lastToken);
  }
  public cast_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new cast_expression(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public unary_expression getUnary_expression() {
    return ((PropertyOne<unary_expression>)getProperty("unary_expression")).getValue();
  }
}
