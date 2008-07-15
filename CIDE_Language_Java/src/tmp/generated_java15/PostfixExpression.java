package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PostfixExpression extends GenASTNode {
  public PostfixExpression(PrimaryExpression primaryExpression, PostfixOp postfixOp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimaryExpression>("primaryExpression", primaryExpression),
      new PropertyZeroOrOne<PostfixOp>("postfixOp", postfixOp)
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
  public PostfixOp getPostfixOp() {
    return ((PropertyZeroOrOne<PostfixOp>)getProperty("postfixOp")).getValue();
  }
}
