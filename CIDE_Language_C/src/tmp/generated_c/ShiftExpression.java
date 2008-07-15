package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ShiftExpression extends GenASTNode {
  public ShiftExpression(AdditiveExpression additiveExpression, ShiftExpressionInt shiftExpressionInt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AdditiveExpression>("additiveExpression", additiveExpression),
      new PropertyZeroOrOne<ShiftExpressionInt>("shiftExpressionInt", shiftExpressionInt)
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
  public ShiftExpressionInt getShiftExpressionInt() {
    return ((PropertyZeroOrOne<ShiftExpressionInt>)getProperty("shiftExpressionInt")).getValue();
  }
}
