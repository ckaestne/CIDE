package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LogicalORExpression extends GenASTNode {
  public LogicalORExpression(LogicalANDExpression logicalANDExpression, ArrayList<LogicalANDExpression> logicalANDExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LogicalANDExpression>("logicalANDExpression", logicalANDExpression),
      new PropertyZeroOrMore<LogicalANDExpression>("logicalANDExpression1", logicalANDExpression1)
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
  public ArrayList<LogicalANDExpression> getLogicalANDExpression1() {
    return ((PropertyZeroOrMore<LogicalANDExpression>)getProperty("logicalANDExpression1")).getValue();
  }
}
