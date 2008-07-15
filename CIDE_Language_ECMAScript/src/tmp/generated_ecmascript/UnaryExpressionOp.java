package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpressionOp extends GenASTNode {
  public UnaryExpressionOp(UnaryOperator unaryOperator, UnaryExpression unaryExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<UnaryOperator>("unaryOperator", unaryOperator),
      new PropertyOne<UnaryExpression>("unaryExpression", unaryExpression)
    }, firstToken, lastToken);
  }
  public UnaryExpressionOp(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpressionOp(cloneProperties(),firstToken,lastToken);
  }
  public UnaryOperator getUnaryOperator() {
    return ((PropertyOne<UnaryOperator>)getProperty("unaryOperator")).getValue();
  }
  public UnaryExpression getUnaryExpression() {
    return ((PropertyOne<UnaryExpression>)getProperty("unaryExpression")).getValue();
  }
}
