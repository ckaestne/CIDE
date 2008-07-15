package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class multiplicative_expressionInternal extends GenASTNode {
  public multiplicative_expressionInternal(multiplicative_operator multiplicative_operator, multiplicative_expression multiplicative_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<multiplicative_operator>("multiplicative_operator", multiplicative_operator),
      new PropertyOne<multiplicative_expression>("multiplicative_expression", multiplicative_expression)
    }, firstToken, lastToken);
  }
  public multiplicative_expressionInternal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new multiplicative_expressionInternal(cloneProperties(),firstToken,lastToken);
  }
  public multiplicative_operator getMultiplicative_operator() {
    return ((PropertyOne<multiplicative_operator>)getProperty("multiplicative_operator")).getValue();
  }
  public multiplicative_expression getMultiplicative_expression() {
    return ((PropertyOne<multiplicative_expression>)getProperty("multiplicative_expression")).getValue();
  }
}
