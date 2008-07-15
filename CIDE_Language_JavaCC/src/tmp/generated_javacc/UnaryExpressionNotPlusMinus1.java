package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpressionNotPlusMinus1 extends UnaryExpressionNotPlusMinus {
  public UnaryExpressionNotPlusMinus1(UnaryOp unaryOp, UnaryExpression unaryExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<UnaryOp>("unaryOp", unaryOp),
      new PropertyOne<UnaryExpression>("unaryExpression", unaryExpression)
    }, firstToken, lastToken);
  }
  public UnaryExpressionNotPlusMinus1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpressionNotPlusMinus1(cloneProperties(),firstToken,lastToken);
  }
  public UnaryOp getUnaryOp() {
    return ((PropertyOne<UnaryOp>)getProperty("unaryOp")).getValue();
  }
  public UnaryExpression getUnaryExpression() {
    return ((PropertyOne<UnaryExpression>)getProperty("unaryExpression")).getValue();
  }
}
