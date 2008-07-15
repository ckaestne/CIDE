package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PostfixExpressionInternal4 extends PostfixExpressionInternal {
  public PostfixExpressionInternal4(ASTStringNode identifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier1", identifier1)
    }, firstToken, lastToken);
  }
  public PostfixExpressionInternal4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PostfixExpressionInternal4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier1() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier1")).getValue();
  }
}
