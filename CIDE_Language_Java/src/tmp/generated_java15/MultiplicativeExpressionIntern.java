package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeExpressionIntern extends GenASTNode {
  public MultiplicativeExpressionIntern(MultiplicativeOp multiplicativeOp, UnaryExpression unaryExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MultiplicativeOp>("multiplicativeOp", multiplicativeOp),
      new PropertyOne<UnaryExpression>("unaryExpression", unaryExpression)
    }, firstToken, lastToken);
  }
  public MultiplicativeExpressionIntern(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultiplicativeExpressionIntern(cloneProperties(),firstToken,lastToken);
  }
  public MultiplicativeOp getMultiplicativeOp() {
    return ((PropertyOne<MultiplicativeOp>)getProperty("multiplicativeOp")).getValue();
  }
  public UnaryExpression getUnaryExpression() {
    return ((PropertyOne<UnaryExpression>)getProperty("unaryExpression")).getValue();
  }
}
