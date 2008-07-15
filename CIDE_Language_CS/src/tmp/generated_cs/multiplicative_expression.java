package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class multiplicative_expression extends GenASTNode {
  public multiplicative_expression(unary_expression unary_expression, multiplicative_expressionInternal multiplicative_expressionInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<unary_expression>("unary_expression", unary_expression),
      new PropertyZeroOrOne<multiplicative_expressionInternal>("multiplicative_expressionInternal", multiplicative_expressionInternal)
    }, firstToken, lastToken);
  }
  public multiplicative_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new multiplicative_expression(cloneProperties(),firstToken,lastToken);
  }
  public unary_expression getUnary_expression() {
    return ((PropertyOne<unary_expression>)getProperty("unary_expression")).getValue();
  }
  public multiplicative_expressionInternal getMultiplicative_expressionInternal() {
    return ((PropertyZeroOrOne<multiplicative_expressionInternal>)getProperty("multiplicative_expressionInternal")).getValue();
  }
}
