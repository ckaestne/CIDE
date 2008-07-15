package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SwitchLabel1 extends SwitchLabel {
  public SwitchLabel1(Expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public SwitchLabel1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SwitchLabel1(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
}
