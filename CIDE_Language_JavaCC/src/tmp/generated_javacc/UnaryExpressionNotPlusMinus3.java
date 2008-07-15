package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpressionNotPlusMinus3 extends UnaryExpressionNotPlusMinus {
  public UnaryExpressionNotPlusMinus3(PostfixExpression postfixExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PostfixExpression>("postfixExpression", postfixExpression)
    }, firstToken, lastToken);
  }
  public UnaryExpressionNotPlusMinus3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpressionNotPlusMinus3(cloneProperties(),firstToken,lastToken);
  }
  public PostfixExpression getPostfixExpression() {
    return ((PropertyOne<PostfixExpression>)getProperty("postfixExpression")).getValue();
  }
}
