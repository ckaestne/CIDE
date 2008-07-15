package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class shift_expression extends GenASTNode {
  public shift_expression(additive_expression additive_expression, shift_expressionInternal shift_expressionInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<additive_expression>("additive_expression", additive_expression),
      new PropertyZeroOrOne<shift_expressionInternal>("shift_expressionInternal", shift_expressionInternal)
    }, firstToken, lastToken);
  }
  public shift_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new shift_expression(cloneProperties(),firstToken,lastToken);
  }
  public additive_expression getAdditive_expression() {
    return ((PropertyOne<additive_expression>)getProperty("additive_expression")).getValue();
  }
  public shift_expressionInternal getShift_expressionInternal() {
    return ((PropertyZeroOrOne<shift_expressionInternal>)getProperty("shift_expressionInternal")).getValue();
  }
}
