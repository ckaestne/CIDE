package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class equality_expression extends GenASTNode {
  public equality_expression(relational_expression relational_expression, equality_expressionInternal equality_expressionInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<relational_expression>("relational_expression", relational_expression),
      new PropertyZeroOrOne<equality_expressionInternal>("equality_expressionInternal", equality_expressionInternal)
    }, firstToken, lastToken);
  }
  public equality_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new equality_expression(cloneProperties(),firstToken,lastToken);
  }
  public relational_expression getRelational_expression() {
    return ((PropertyOne<relational_expression>)getProperty("relational_expression")).getValue();
  }
  public equality_expressionInternal getEquality_expressionInternal() {
    return ((PropertyZeroOrOne<equality_expressionInternal>)getProperty("equality_expressionInternal")).getValue();
  }
}
