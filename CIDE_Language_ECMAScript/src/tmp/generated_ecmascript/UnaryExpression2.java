package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpression2 extends UnaryExpression {
  public UnaryExpression2(ArrayList<UnaryExpressionOp> unaryExpressionOp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<UnaryExpressionOp>("unaryExpressionOp", unaryExpressionOp)
    }, firstToken, lastToken);
  }
  public UnaryExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpression2(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<UnaryExpressionOp> getUnaryExpressionOp() {
    return ((PropertyOneOrMore<UnaryExpressionOp>)getProperty("unaryExpressionOp")).getValue();
  }
}
