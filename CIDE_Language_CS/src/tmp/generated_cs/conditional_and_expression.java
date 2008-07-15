package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class conditional_and_expression extends GenASTNode {
  public conditional_and_expression(inclusive_or_expression inclusive_or_expression, conditional_and_expression conditional_and_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<inclusive_or_expression>("inclusive_or_expression", inclusive_or_expression),
      new PropertyZeroOrOne<conditional_and_expression>("conditional_and_expression", conditional_and_expression)
    }, firstToken, lastToken);
  }
  public conditional_and_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new conditional_and_expression(cloneProperties(),firstToken,lastToken);
  }
  public inclusive_or_expression getInclusive_or_expression() {
    return ((PropertyOne<inclusive_or_expression>)getProperty("inclusive_or_expression")).getValue();
  }
  public conditional_and_expression getConditional_and_expression() {
    return ((PropertyZeroOrOne<conditional_and_expression>)getProperty("conditional_and_expression")).getValue();
  }
}
