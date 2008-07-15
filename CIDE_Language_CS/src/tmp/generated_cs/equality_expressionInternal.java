package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class equality_expressionInternal extends GenASTNode {
  public equality_expressionInternal(equality_operator equality_operator, equality_expression equality_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<equality_operator>("equality_operator", equality_operator),
      new PropertyOne<equality_expression>("equality_expression", equality_expression)
    }, firstToken, lastToken);
  }
  public equality_expressionInternal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new equality_expressionInternal(cloneProperties(),firstToken,lastToken);
  }
  public equality_operator getEquality_operator() {
    return ((PropertyOne<equality_operator>)getProperty("equality_operator")).getValue();
  }
  public equality_expression getEquality_expression() {
    return ((PropertyOne<equality_expression>)getProperty("equality_expression")).getValue();
  }
}
