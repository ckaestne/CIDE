package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ShiftExpression extends GenASTNode {
  public ShiftExpression(AdditiveExpression additiveExpression, ArrayList<ShiftExpressionEnd> shiftExpressionEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AdditiveExpression>("additiveExpression", additiveExpression),
      new PropertyZeroOrMore<ShiftExpressionEnd>("shiftExpressionEnd", shiftExpressionEnd)
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
  public ArrayList<ShiftExpressionEnd> getShiftExpressionEnd() {
    return ((PropertyZeroOrMore<ShiftExpressionEnd>)getProperty("shiftExpressionEnd")).getValue();
  }
}
