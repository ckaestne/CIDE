package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalExpression extends GenASTNode {
  public ConditionalExpression(LogicalORExpression logicalORExpression, ConditionalExpressionEnd conditionalExpressionEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LogicalORExpression>("logicalORExpression", logicalORExpression),
      new PropertyZeroOrOne<ConditionalExpressionEnd>("conditionalExpressionEnd", conditionalExpressionEnd)
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
  public ConditionalExpressionEnd getConditionalExpressionEnd() {
    return ((PropertyZeroOrOne<ConditionalExpressionEnd>)getProperty("conditionalExpressionEnd")).getValue();
  }
}
