package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalExpression extends GenASTNode {
  public ConditionalExpression(LogicalORExpression logicalORExpression, ConditionalExpressionInternal conditionalExpressionInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LogicalORExpression>("logicalORExpression", logicalORExpression),
      new PropertyZeroOrOne<ConditionalExpressionInternal>("conditionalExpressionInternal", conditionalExpressionInternal)
    }, firstToken, lastToken);
  }
  public ConditionalExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConditionalExpression(cloneProperties(),firstToken,lastToken);
  }
  public LogicalORExpression getLogicalORExpression() {
    return ((PropertyOne<LogicalORExpression>)getProperty("logicalORExpression")).getValue();
  }
  public ConditionalExpressionInternal getConditionalExpressionInternal() {
    return ((PropertyZeroOrOne<ConditionalExpressionInternal>)getProperty("conditionalExpressionInternal")).getValue();
  }
}
