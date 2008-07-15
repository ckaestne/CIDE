package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpressionNoIn extends GenASTNode {
  public RelationalExpressionNoIn(ShiftExpression shiftExpression, ArrayList<RelationalExpressionNoInEnd> relationalExpressionNoInEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ShiftExpression>("shiftExpression", shiftExpression),
      new PropertyZeroOrMore<RelationalExpressionNoInEnd>("relationalExpressionNoInEnd", relationalExpressionNoInEnd)
    }, firstToken, lastToken);
  }
  public RelationalExpressionNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RelationalExpressionNoIn(cloneProperties(),firstToken,lastToken);
  }
  public ShiftExpression getShiftExpression() {
    return ((PropertyOne<ShiftExpression>)getProperty("shiftExpression")).getValue();
  }
  public ArrayList<RelationalExpressionNoInEnd> getRelationalExpressionNoInEnd() {
    return ((PropertyZeroOrMore<RelationalExpressionNoInEnd>)getProperty("relationalExpressionNoInEnd")).getValue();
  }
}
