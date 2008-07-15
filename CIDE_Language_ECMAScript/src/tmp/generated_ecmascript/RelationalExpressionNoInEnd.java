package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpressionNoInEnd extends GenASTNode {
  public RelationalExpressionNoInEnd(RelationalNoInOperator relationalNoInOperator, ShiftExpression shiftExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalNoInOperator>("relationalNoInOperator", relationalNoInOperator),
      new PropertyOne<ShiftExpression>("shiftExpression", shiftExpression)
    }, firstToken, lastToken);
  }
  public RelationalExpressionNoInEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RelationalExpressionNoInEnd(cloneProperties(),firstToken,lastToken);
  }
  public RelationalNoInOperator getRelationalNoInOperator() {
    return ((PropertyOne<RelationalNoInOperator>)getProperty("relationalNoInOperator")).getValue();
  }
  public ShiftExpression getShiftExpression() {
    return ((PropertyOne<ShiftExpression>)getProperty("shiftExpression")).getValue();
  }
}
