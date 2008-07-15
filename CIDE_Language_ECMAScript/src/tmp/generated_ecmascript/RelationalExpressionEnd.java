package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpressionEnd extends GenASTNode {
  public RelationalExpressionEnd(RelationalOperator relationalOperator, ShiftExpression shiftExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalOperator>("relationalOperator", relationalOperator),
      new PropertyOne<ShiftExpression>("shiftExpression", shiftExpression)
    }, firstToken, lastToken);
  }
  public RelationalExpressionEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RelationalExpressionEnd(cloneProperties(),firstToken,lastToken);
  }
  public RelationalOperator getRelationalOperator() {
    return ((PropertyOne<RelationalOperator>)getProperty("relationalOperator")).getValue();
  }
  public ShiftExpression getShiftExpression() {
    return ((PropertyOne<ShiftExpression>)getProperty("shiftExpression")).getValue();
  }
}
