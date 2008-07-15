package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpression extends GenASTNode {
  public RelationalExpression(ShiftExpression shiftExpression, ArrayList<RelationalExpressionEnd> relationalExpressionEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ShiftExpression>("shiftExpression", shiftExpression),
      new PropertyZeroOrMore<RelationalExpressionEnd>("relationalExpressionEnd", relationalExpressionEnd)
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
  public ArrayList<RelationalExpressionEnd> getRelationalExpressionEnd() {
    return ((PropertyZeroOrMore<RelationalExpressionEnd>)getProperty("relationalExpressionEnd")).getValue();
  }
}
