package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class shift_expressionInternal extends GenASTNode {
  public shift_expressionInternal(shift_operator shift_operator, shift_expression shift_expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<shift_operator>("shift_operator", shift_operator),
      new PropertyOne<shift_expression>("shift_expression", shift_expression)
    }, firstToken, lastToken);
  }
  public shift_expressionInternal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new shift_expressionInternal(cloneProperties(),firstToken,lastToken);
  }
  public shift_operator getShift_operator() {
    return ((PropertyOne<shift_operator>)getProperty("shift_operator")).getValue();
  }
  public shift_expression getShift_expression() {
    return ((PropertyOne<shift_expression>)getProperty("shift_expression")).getValue();
  }
}
