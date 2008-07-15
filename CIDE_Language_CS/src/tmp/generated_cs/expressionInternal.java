package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expressionInternal extends GenASTNode {
  public expressionInternal(assignment_operator assignment_operator, expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<assignment_operator>("assignment_operator", assignment_operator),
      new PropertyOne<expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public expressionInternal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expressionInternal(cloneProperties(),firstToken,lastToken);
  }
  public assignment_operator getAssignment_operator() {
    return ((PropertyOne<assignment_operator>)getProperty("assignment_operator")).getValue();
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
}
