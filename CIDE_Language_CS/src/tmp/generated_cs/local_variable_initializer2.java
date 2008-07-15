package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class local_variable_initializer2 extends local_variable_initializer {
  public local_variable_initializer2(expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public local_variable_initializer2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new local_variable_initializer2(cloneProperties(),firstToken,lastToken);
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
}
