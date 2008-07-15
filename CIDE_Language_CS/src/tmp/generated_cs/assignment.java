package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class assignment extends GenASTNode {
  public assignment(conditional_expression conditional_expression, assignment_operator assignment_operator, expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<conditional_expression>("conditional_expression", conditional_expression),
      new PropertyOne<assignment_operator>("assignment_operator", assignment_operator),
      new PropertyOne<expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public assignment(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new assignment(cloneProperties(),firstToken,lastToken);
  }
  public conditional_expression getConditional_expression() {
    return ((PropertyOne<conditional_expression>)getProperty("conditional_expression")).getValue();
  }
  public assignment_operator getAssignment_operator() {
    return ((PropertyOne<assignment_operator>)getProperty("assignment_operator")).getValue();
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
}
