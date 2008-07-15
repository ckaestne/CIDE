package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class primary_expression_start3 extends primary_expression_start {
  public primary_expression_start3(parenthesized_expression parenthesized_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<parenthesized_expression>("parenthesized_expression", parenthesized_expression)
    }, firstToken, lastToken);
  }
  public primary_expression_start3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new primary_expression_start3(cloneProperties(),firstToken,lastToken);
  }
  public parenthesized_expression getParenthesized_expression() {
    return ((PropertyOne<parenthesized_expression>)getProperty("parenthesized_expression")).getValue();
  }
}
