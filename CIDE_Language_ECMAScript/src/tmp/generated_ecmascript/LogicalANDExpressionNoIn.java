package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LogicalANDExpressionNoIn extends GenASTNode {
  public LogicalANDExpressionNoIn(BitwiseORExpressionNoIn bitwiseORExpressionNoIn, ArrayList<BitwiseORExpressionNoIn> bitwiseORExpressionNoIn1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BitwiseORExpressionNoIn>("bitwiseORExpressionNoIn", bitwiseORExpressionNoIn),
      new PropertyZeroOrMore<BitwiseORExpressionNoIn>("bitwiseORExpressionNoIn1", bitwiseORExpressionNoIn1)
    }, firstToken, lastToken);
  }
  public LogicalANDExpressionNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LogicalANDExpressionNoIn(cloneProperties(),firstToken,lastToken);
  }
  public BitwiseORExpressionNoIn getBitwiseORExpressionNoIn() {
    return ((PropertyOne<BitwiseORExpressionNoIn>)getProperty("bitwiseORExpressionNoIn")).getValue();
  }
  public ArrayList<BitwiseORExpressionNoIn> getBitwiseORExpressionNoIn1() {
    return ((PropertyZeroOrMore<BitwiseORExpressionNoIn>)getProperty("bitwiseORExpressionNoIn1")).getValue();
  }
}
