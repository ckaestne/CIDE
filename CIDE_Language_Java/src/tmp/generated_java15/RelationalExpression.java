package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpression extends GenASTNode {
  public RelationalExpression(ShiftExpression shiftExpression, ArrayList<RelationalExpressionIntern> relationalExpressionIntern, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ShiftExpression>("shiftExpression", shiftExpression),
      new PropertyZeroOrMore<RelationalExpressionIntern>("relationalExpressionIntern", relationalExpressionIntern)
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
  public ArrayList<RelationalExpressionIntern> getRelationalExpressionIntern() {
    return ((PropertyZeroOrMore<RelationalExpressionIntern>)getProperty("relationalExpressionIntern")).getValue();
  }
}
