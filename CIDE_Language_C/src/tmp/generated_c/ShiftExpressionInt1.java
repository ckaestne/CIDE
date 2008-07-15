package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ShiftExpressionInt1 extends ShiftExpressionInt {
  public ShiftExpressionInt1(ShiftExpression shiftExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ShiftExpression>("shiftExpression", shiftExpression)
    }, firstToken, lastToken);
  }
  public ShiftExpressionInt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ShiftExpressionInt1(cloneProperties(),firstToken,lastToken);
  }
  public ShiftExpression getShiftExpression() {
    return ((PropertyOne<ShiftExpression>)getProperty("shiftExpression")).getValue();
  }
}
