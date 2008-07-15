package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectAbstractDeclaratorP21 extends DirectAbstractDeclaratorP2 {
  public DirectAbstractDeclaratorP21(ConstantExpression constantExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ConstantExpression>("constantExpression", constantExpression)
    }, firstToken, lastToken);
  }
  public DirectAbstractDeclaratorP21(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectAbstractDeclaratorP21(cloneProperties(),firstToken,lastToken);
  }
  public ConstantExpression getConstantExpression() {
    return ((PropertyZeroOrOne<ConstantExpression>)getProperty("constantExpression")).getValue();
  }
}
