package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RelationalExpressionIntern extends GenASTNode {
  public RelationalExpressionIntern(RelationalOp relationalOp, ShiftExpression shiftExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<RelationalOp>("relationalOp", relationalOp),
      new PropertyOne<ShiftExpression>("shiftExpression", shiftExpression)
    }, firstToken, lastToken);
  }
  public RelationalExpressionIntern(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new RelationalExpressionIntern(cloneProperties(),firstToken,lastToken);
  }
  public RelationalOp getRelationalOp() {
    return ((PropertyOne<RelationalOp>)getProperty("relationalOp")).getValue();
  }
  public ShiftExpression getShiftExpression() {
    return ((PropertyOne<ShiftExpression>)getProperty("shiftExpression")).getValue();
  }
}
