package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LogicalORExpression extends GenASTNode {
  public LogicalORExpression(LogicalANDExpression logicalANDExpression, LogicalORExpression logicalORExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LogicalANDExpression>("logicalANDExpression", logicalANDExpression),
      new PropertyZeroOrOne<LogicalORExpression>("logicalORExpression", logicalORExpression)
    }, firstToken, lastToken);
  }
  public LogicalORExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LogicalORExpression(cloneProperties(),firstToken,lastToken);
  }
  public LogicalANDExpression getLogicalANDExpression() {
    return ((PropertyOne<LogicalANDExpression>)getProperty("logicalANDExpression")).getValue();
  }
  public LogicalORExpression getLogicalORExpression() {
    return ((PropertyZeroOrOne<LogicalORExpression>)getProperty("logicalORExpression")).getValue();
  }
}
