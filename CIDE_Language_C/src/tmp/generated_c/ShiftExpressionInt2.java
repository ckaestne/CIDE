package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ShiftExpressionInt2 extends ShiftExpressionInt {
  public ShiftExpressionInt2(ShiftExpression shiftExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ShiftExpression>("shiftExpression1", shiftExpression1)
    }, firstToken, lastToken);
  }
  public ShiftExpressionInt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ShiftExpressionInt2(cloneProperties(),firstToken,lastToken);
  }
  public ShiftExpression getShiftExpression1() {
    return ((PropertyOne<ShiftExpression>)getProperty("shiftExpression1")).getValue();
  }
}
