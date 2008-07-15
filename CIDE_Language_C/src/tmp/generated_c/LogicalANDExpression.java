package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LogicalANDExpression extends GenASTNode {
  public LogicalANDExpression(InclusiveORExpression inclusiveORExpression, LogicalANDExpression logicalANDExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<InclusiveORExpression>("inclusiveORExpression", inclusiveORExpression),
      new PropertyZeroOrOne<LogicalANDExpression>("logicalANDExpression", logicalANDExpression)
    }, firstToken, lastToken);
  }
  public LogicalANDExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LogicalANDExpression(cloneProperties(),firstToken,lastToken);
  }
  public InclusiveORExpression getInclusiveORExpression() {
    return ((PropertyOne<InclusiveORExpression>)getProperty("inclusiveORExpression")).getValue();
  }
  public LogicalANDExpression getLogicalANDExpression() {
    return ((PropertyZeroOrOne<LogicalANDExpression>)getProperty("logicalANDExpression")).getValue();
  }
}
