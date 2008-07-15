package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LogicalANDExpression extends GenASTNode {
  public LogicalANDExpression(BitwiseORExpression bitwiseORExpression, ArrayList<BitwiseORExpression> bitwiseORExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BitwiseORExpression>("bitwiseORExpression", bitwiseORExpression),
      new PropertyZeroOrMore<BitwiseORExpression>("bitwiseORExpression1", bitwiseORExpression1)
    }, firstToken, lastToken);
  }
  public LogicalANDExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LogicalANDExpression(cloneProperties(),firstToken,lastToken);
  }
  public BitwiseORExpression getBitwiseORExpression() {
    return ((PropertyOne<BitwiseORExpression>)getProperty("bitwiseORExpression")).getValue();
  }
  public ArrayList<BitwiseORExpression> getBitwiseORExpression1() {
    return ((PropertyZeroOrMore<BitwiseORExpression>)getProperty("bitwiseORExpression1")).getValue();
  }
}
