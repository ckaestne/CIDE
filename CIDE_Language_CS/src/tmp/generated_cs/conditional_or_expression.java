package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class conditional_or_expression extends GenASTNode {
  public conditional_or_expression(conditional_and_expression conditional_and_expression, conditional_or_expression conditional_or_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<conditional_and_expression>("conditional_and_expression", conditional_and_expression),
      new PropertyZeroOrOne<conditional_or_expression>("conditional_or_expression", conditional_or_expression)
    }, firstToken, lastToken);
  }
  public conditional_or_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new conditional_or_expression(cloneProperties(),firstToken,lastToken);
  }
  public conditional_and_expression getConditional_and_expression() {
    return ((PropertyOne<conditional_and_expression>)getProperty("conditional_and_expression")).getValue();
  }
  public conditional_or_expression getConditional_or_expression() {
    return ((PropertyZeroOrOne<conditional_or_expression>)getProperty("conditional_or_expression")).getValue();
  }
}
