package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class additive_expression extends GenASTNode {
  public additive_expression(multiplicative_expression multiplicative_expression, additive_expressionInternal additive_expressionInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<multiplicative_expression>("multiplicative_expression", multiplicative_expression),
      new PropertyZeroOrOne<additive_expressionInternal>("additive_expressionInternal", additive_expressionInternal)
    }, firstToken, lastToken);
  }
  public additive_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new additive_expression(cloneProperties(),firstToken,lastToken);
  }
  public multiplicative_expression getMultiplicative_expression() {
    return ((PropertyOne<multiplicative_expression>)getProperty("multiplicative_expression")).getValue();
  }
  public additive_expressionInternal getAdditive_expressionInternal() {
    return ((PropertyZeroOrOne<additive_expressionInternal>)getProperty("additive_expressionInternal")).getValue();
  }
}
