package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class relational_expression extends GenASTNode {
  public relational_expression(shift_expression shift_expression, relational_expressionInternal relational_expressionInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<shift_expression>("shift_expression", shift_expression),
      new PropertyZeroOrOne<relational_expressionInternal>("relational_expressionInternal", relational_expressionInternal)
    }, firstToken, lastToken);
  }
  public relational_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new relational_expression(cloneProperties(),firstToken,lastToken);
  }
  public shift_expression getShift_expression() {
    return ((PropertyOne<shift_expression>)getProperty("shift_expression")).getValue();
  }
  public relational_expressionInternal getRelational_expressionInternal() {
    return ((PropertyZeroOrOne<relational_expressionInternal>)getProperty("relational_expressionInternal")).getValue();
  }
}
