package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class conditional_expressionInternal extends GenASTNode {
  public conditional_expressionInternal(expression expression, conditional_expression conditional_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expression>("expression", expression),
      new PropertyOne<conditional_expression>("conditional_expression", conditional_expression)
    }, firstToken, lastToken);
  }
  public conditional_expressionInternal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new conditional_expressionInternal(cloneProperties(),firstToken,lastToken);
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
  public conditional_expression getConditional_expression() {
    return ((PropertyOne<conditional_expression>)getProperty("conditional_expression")).getValue();
  }
}
