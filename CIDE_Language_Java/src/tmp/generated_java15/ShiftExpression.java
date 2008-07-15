package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ShiftExpression extends GenASTNode {
  public ShiftExpression(AdditiveExpression additiveExpression, ArrayList<ShiftExpressionRight> shiftExpressionRight, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AdditiveExpression>("additiveExpression", additiveExpression),
      new PropertyZeroOrMore<ShiftExpressionRight>("shiftExpressionRight", shiftExpressionRight)
    }, firstToken, lastToken);
  }
  public ShiftExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ShiftExpression(cloneProperties(),firstToken,lastToken);
  }
  public AdditiveExpression getAdditiveExpression() {
    return ((PropertyOne<AdditiveExpression>)getProperty("additiveExpression")).getValue();
  }
  public ArrayList<ShiftExpressionRight> getShiftExpressionRight() {
    return ((PropertyZeroOrMore<ShiftExpressionRight>)getProperty("shiftExpressionRight")).getValue();
  }
}
