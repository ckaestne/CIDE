package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_start8 extends primary_expression_start {
  public primary_expression_start8(sizeof_expression sizeof_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<sizeof_expression>("sizeof_expression", sizeof_expression)
    }, firstToken, lastToken);
  }
  public primary_expression_start8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_start8(cloneProperties(),firstToken,lastToken);
  }
  public sizeof_expression getSizeof_expression() {
    return ((PropertyOne<sizeof_expression>)getProperty("sizeof_expression")).getValue();
  }
}
