package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpression extends GenASTNode {
  public RelationalExpression(ShiftExpression shiftExpression, RelationalExpressionInt relationalExpressionInt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ShiftExpression>("shiftExpression", shiftExpression),
      new PropertyZeroOrOne<RelationalExpressionInt>("relationalExpressionInt", relationalExpressionInt)
    }, firstToken, lastToken);
  }
  public RelationalExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RelationalExpression(cloneProperties(),firstToken,lastToken);
  }
  public ShiftExpression getShiftExpression() {
    return ((PropertyOne<ShiftExpression>)getProperty("shiftExpression")).getValue();
  }
  public RelationalExpressionInt getRelationalExpressionInt() {
    return ((PropertyZeroOrOne<RelationalExpressionInt>)getProperty("relationalExpressionInt")).getValue();
  }
}
