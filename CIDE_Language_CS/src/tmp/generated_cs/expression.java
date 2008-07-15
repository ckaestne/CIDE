package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expression extends GenASTNode {
  public expression(conditional_expression conditional_expression, expressionInternal expressionInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<conditional_expression>("conditional_expression", conditional_expression),
      new PropertyZeroOrOne<expressionInternal>("expressionInternal", expressionInternal)
    }, firstToken, lastToken);
  }
  public expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expression(cloneProperties(),firstToken,lastToken);
  }
  public conditional_expression getConditional_expression() {
    return ((PropertyOne<conditional_expression>)getProperty("conditional_expression")).getValue();
  }
  public expressionInternal getExpressionInternal() {
    return ((PropertyZeroOrOne<expressionInternal>)getProperty("expressionInternal")).getValue();
  }
}
