package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PostfixExpression extends GenASTNode {
  public PostfixExpression(LeftHandSideExpression leftHandSideExpression, PostfixOperator postfixOperator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LeftHandSideExpression>("leftHandSideExpression", leftHandSideExpression),
      new PropertyZeroOrOne<PostfixOperator>("postfixOperator", postfixOperator)
    }, firstToken, lastToken);
  }
  public PostfixExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PostfixExpression(cloneProperties(),firstToken,lastToken);
  }
  public LeftHandSideExpression getLeftHandSideExpression() {
    return ((PropertyOne<LeftHandSideExpression>)getProperty("leftHandSideExpression")).getValue();
  }
  public PostfixOperator getPostfixOperator() {
    return ((PropertyZeroOrOne<PostfixOperator>)getProperty("postfixOperator")).getValue();
  }
}
