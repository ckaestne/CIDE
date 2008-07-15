package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class relational_expressionInternal1 extends relational_expressionInternal {
  public relational_expressionInternal1(relational_operator relational_operator, relational_expression relational_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<relational_operator>("relational_operator", relational_operator),
      new PropertyOne<relational_expression>("relational_expression", relational_expression)
    }, firstToken, lastToken);
  }
  public relational_expressionInternal1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new relational_expressionInternal1(cloneProperties(),firstToken,lastToken);
  }
  public relational_operator getRelational_operator() {
    return ((PropertyOne<relational_operator>)getProperty("relational_operator")).getValue();
  }
  public relational_expression getRelational_expression() {
    return ((PropertyOne<relational_expression>)getProperty("relational_expression")).getValue();
  }
}
