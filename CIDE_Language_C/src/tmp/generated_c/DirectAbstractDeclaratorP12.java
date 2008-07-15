package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectAbstractDeclaratorP12 extends DirectAbstractDeclaratorP1 {
  public DirectAbstractDeclaratorP12(ConstantExpression constantExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ConstantExpression>("constantExpression", constantExpression)
    }, firstToken, lastToken);
  }
  public DirectAbstractDeclaratorP12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectAbstractDeclaratorP12(cloneProperties(),firstToken,lastToken);
  }
  public ConstantExpression getConstantExpression() {
    return ((PropertyZeroOrOne<ConstantExpression>)getProperty("constantExpression")).getValue();
  }
}
