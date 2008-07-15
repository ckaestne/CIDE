package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeExpressionEnd extends GenASTNode {
  public MultiplicativeExpressionEnd(MultiplicativeOperator multiplicativeOperator, UnaryExpression unaryExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MultiplicativeOperator>("multiplicativeOperator", multiplicativeOperator),
      new PropertyOne<UnaryExpression>("unaryExpression", unaryExpression)
    }, firstToken, lastToken);
  }
  public MultiplicativeExpressionEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultiplicativeExpressionEnd(cloneProperties(),firstToken,lastToken);
  }
  public MultiplicativeOperator getMultiplicativeOperator() {
    return ((PropertyOne<MultiplicativeOperator>)getProperty("multiplicativeOperator")).getValue();
  }
  public UnaryExpression getUnaryExpression() {
    return ((PropertyOne<UnaryExpression>)getProperty("unaryExpression")).getValue();
  }
}
