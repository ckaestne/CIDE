package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PostfixExpression extends GenASTNode {
  public PostfixExpression(PrimaryExpression primaryExpression, ArrayList<PostfixExpressionInternal> postfixExpressionInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimaryExpression>("primaryExpression", primaryExpression),
      new PropertyZeroOrMore<PostfixExpressionInternal>("postfixExpressionInternal", postfixExpressionInternal)
    }, firstToken, lastToken);
  }
  public PostfixExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PostfixExpression(cloneProperties(),firstToken,lastToken);
  }
  public PrimaryExpression getPrimaryExpression() {
    return ((PropertyOne<PrimaryExpression>)getProperty("primaryExpression")).getValue();
  }
  public ArrayList<PostfixExpressionInternal> getPostfixExpressionInternal() {
    return ((PropertyZeroOrMore<PostfixExpressionInternal>)getProperty("postfixExpressionInternal")).getValue();
  }
}
