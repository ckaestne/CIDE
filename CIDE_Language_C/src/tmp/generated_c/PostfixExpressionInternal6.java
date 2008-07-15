package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PostfixExpressionInternal6 extends PostfixExpressionInternal {
  public PostfixExpressionInternal6(ASTStringNode minusminus, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("minusminus", minusminus)
    }, firstToken, lastToken);
  }
  public PostfixExpressionInternal6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PostfixExpressionInternal6(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getMinusminus() {
    return ((PropertyOne<ASTStringNode>)getProperty("minusminus")).getValue();
  }
}
