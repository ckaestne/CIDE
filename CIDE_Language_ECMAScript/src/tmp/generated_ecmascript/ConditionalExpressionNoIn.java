package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalExpressionNoIn extends GenASTNode {
  public ConditionalExpressionNoIn(LogicalORExpressionNoIn logicalORExpressionNoIn, ConditionalExpressionNoInEnd conditionalExpressionNoInEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LogicalORExpressionNoIn>("logicalORExpressionNoIn", logicalORExpressionNoIn),
      new PropertyZeroOrOne<ConditionalExpressionNoInEnd>("conditionalExpressionNoInEnd", conditionalExpressionNoInEnd)
    }, firstToken, lastToken);
  }
  public ConditionalExpressionNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConditionalExpressionNoIn(cloneProperties(),firstToken,lastToken);
  }
  public LogicalORExpressionNoIn getLogicalORExpressionNoIn() {
    return ((PropertyOne<LogicalORExpressionNoIn>)getProperty("logicalORExpressionNoIn")).getValue();
  }
  public ConditionalExpressionNoInEnd getConditionalExpressionNoInEnd() {
    return ((PropertyZeroOrOne<ConditionalExpressionNoInEnd>)getProperty("conditionalExpressionNoInEnd")).getValue();
  }
}
