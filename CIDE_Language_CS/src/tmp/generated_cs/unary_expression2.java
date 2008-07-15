package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class unary_expression2 extends unary_expression {
  public unary_expression2(cast_expression cast_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<cast_expression>("cast_expression", cast_expression)
    }, firstToken, lastToken);
  }
  public unary_expression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new unary_expression2(cloneProperties(),firstToken,lastToken);
  }
  public cast_expression getCast_expression() {
    return ((PropertyOne<cast_expression>)getProperty("cast_expression")).getValue();
  }
}
