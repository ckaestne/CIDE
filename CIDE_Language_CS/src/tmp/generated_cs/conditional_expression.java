package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class conditional_expression extends GenASTNode {
  public conditional_expression(conditional_or_expression conditional_or_expression, conditional_expressionInternal conditional_expressionInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<conditional_or_expression>("conditional_or_expression", conditional_or_expression),
      new PropertyZeroOrOne<conditional_expressionInternal>("conditional_expressionInternal", conditional_expressionInternal)
    }, firstToken, lastToken);
  }
  public conditional_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new conditional_expression(cloneProperties(),firstToken,lastToken);
  }
  public conditional_or_expression getConditional_or_expression() {
    return ((PropertyOne<conditional_or_expression>)getProperty("conditional_or_expression")).getValue();
  }
  public conditional_expressionInternal getConditional_expressionInternal() {
    return ((PropertyZeroOrOne<conditional_expressionInternal>)getProperty("conditional_expressionInternal")).getValue();
  }
}
