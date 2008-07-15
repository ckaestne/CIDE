package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpression1 extends UnaryExpression {
  public UnaryExpression1(PostfixExpression postfixExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PostfixExpression>("postfixExpression", postfixExpression)
    }, firstToken, lastToken);
  }
  public UnaryExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpression1(cloneProperties(),firstToken,lastToken);
  }
  public PostfixExpression getPostfixExpression() {
    return ((PropertyOne<PostfixExpression>)getProperty("postfixExpression")).getValue();
  }
}
