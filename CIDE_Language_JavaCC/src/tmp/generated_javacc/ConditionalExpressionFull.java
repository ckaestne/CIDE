package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalExpressionFull extends GenASTNode {
  public ConditionalExpressionFull(ConditionalOrExpression conditionalOrExpression, Expression expression, Expression expression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ConditionalOrExpression>("conditionalOrExpression", conditionalOrExpression),
      new PropertyOne<Expression>("expression", expression),
      new PropertyOne<Expression>("expression1", expression1)
    }, firstToken, lastToken);
  }
  public ConditionalExpressionFull(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConditionalExpressionFull(cloneProperties(),firstToken,lastToken);
  }
  public ConditionalOrExpression getConditionalOrExpression() {
    return ((PropertyOne<ConditionalOrExpression>)getProperty("conditionalOrExpression")).getValue();
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public Expression getExpression1() {
    return ((PropertyOne<Expression>)getProperty("expression1")).getValue();
  }
}
