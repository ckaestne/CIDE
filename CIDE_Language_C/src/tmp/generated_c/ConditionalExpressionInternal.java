package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalExpressionInternal extends GenASTNode {
  public ConditionalExpressionInternal(Expression expression, ConditionalExpression conditionalExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyOne<ConditionalExpression>("conditionalExpression", conditionalExpression)
    }, firstToken, lastToken);
  }
  public ConditionalExpressionInternal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConditionalExpressionInternal(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public ConditionalExpression getConditionalExpression() {
    return ((PropertyOne<ConditionalExpression>)getProperty("conditionalExpression")).getValue();
  }
}
