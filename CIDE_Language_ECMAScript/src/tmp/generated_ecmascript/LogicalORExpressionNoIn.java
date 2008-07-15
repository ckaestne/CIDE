package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LogicalORExpressionNoIn extends GenASTNode {
  public LogicalORExpressionNoIn(LogicalANDExpressionNoIn logicalANDExpressionNoIn, ArrayList<LogicalANDExpressionNoIn> logicalANDExpressionNoIn1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LogicalANDExpressionNoIn>("logicalANDExpressionNoIn", logicalANDExpressionNoIn),
      new PropertyZeroOrMore<LogicalANDExpressionNoIn>("logicalANDExpressionNoIn1", logicalANDExpressionNoIn1)
    }, firstToken, lastToken);
  }
  public LogicalORExpressionNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LogicalORExpressionNoIn(cloneProperties(),firstToken,lastToken);
  }
  public LogicalANDExpressionNoIn getLogicalANDExpressionNoIn() {
    return ((PropertyOne<LogicalANDExpressionNoIn>)getProperty("logicalANDExpressionNoIn")).getValue();
  }
  public ArrayList<LogicalANDExpressionNoIn> getLogicalANDExpressionNoIn1() {
    return ((PropertyZeroOrMore<LogicalANDExpressionNoIn>)getProperty("logicalANDExpressionNoIn1")).getValue();
  }
}
