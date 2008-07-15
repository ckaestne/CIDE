package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ShiftExpressionEnd extends GenASTNode {
  public ShiftExpressionEnd(ShiftOperator shiftOperator, AdditiveExpression additiveExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ShiftOperator>("shiftOperator", shiftOperator),
      new PropertyOne<AdditiveExpression>("additiveExpression", additiveExpression)
    }, firstToken, lastToken);
  }
  public ShiftExpressionEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ShiftExpressionEnd(cloneProperties(),firstToken,lastToken);
  }
  public ShiftOperator getShiftOperator() {
    return ((PropertyOne<ShiftOperator>)getProperty("shiftOperator")).getValue();
  }
  public AdditiveExpression getAdditiveExpression() {
    return ((PropertyOne<AdditiveExpression>)getProperty("additiveExpression")).getValue();
  }
}
