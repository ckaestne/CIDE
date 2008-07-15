package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PostfixExpressionInternal5 extends PostfixExpressionInternal {
  public PostfixExpressionInternal5(ASTStringNode plusplus, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("plusplus", plusplus)
    }, firstToken, lastToken);
  }
  public PostfixExpressionInternal5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PostfixExpressionInternal5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPlusplus() {
    return ((PropertyOne<ASTStringNode>)getProperty("plusplus")).getValue();
  }
}
