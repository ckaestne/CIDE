package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PostfixExpressionInternal2 extends PostfixExpressionInternal {
  public PostfixExpressionInternal2(ArgumentExpressionList argumentExpressionList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ArgumentExpressionList>("argumentExpressionList", argumentExpressionList)
    }, firstToken, lastToken);
  }
  public PostfixExpressionInternal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PostfixExpressionInternal2(cloneProperties(),firstToken,lastToken);
  }
  public ArgumentExpressionList getArgumentExpressionList() {
    return ((PropertyZeroOrOne<ArgumentExpressionList>)getProperty("argumentExpressionList")).getValue();
  }
}
