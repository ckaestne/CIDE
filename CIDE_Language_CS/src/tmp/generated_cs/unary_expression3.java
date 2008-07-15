package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class unary_expression3 extends unary_expression {
  public unary_expression3(primary_expression primary_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<primary_expression>("primary_expression", primary_expression)
    }, firstToken, lastToken);
  }
  public unary_expression3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new unary_expression3(cloneProperties(),firstToken,lastToken);
  }
  public primary_expression getPrimary_expression() {
    return ((PropertyOne<primary_expression>)getProperty("primary_expression")).getValue();
  }
}
