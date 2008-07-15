package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectDeclaratorP21 extends DirectDeclaratorP2 {
  public DirectDeclaratorP21(ConstantExpression constantExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ConstantExpression>("constantExpression", constantExpression)
    }, firstToken, lastToken);
  }
  public DirectDeclaratorP21(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectDeclaratorP21(cloneProperties(),firstToken,lastToken);
  }
  public ConstantExpression getConstantExpression() {
    return ((PropertyZeroOrOne<ConstantExpression>)getProperty("constantExpression")).getValue();
  }
}
