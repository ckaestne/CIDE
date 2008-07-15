package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalExpressionEnd extends GenASTNode {
  public ConditionalExpressionEnd(AssignmentExpression assignmentExpression, AssignmentExpression assignmentExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AssignmentExpression>("assignmentExpression", assignmentExpression),
      new PropertyOne<AssignmentExpression>("assignmentExpression1", assignmentExpression1)
    }, firstToken, lastToken);
  }
  public ConditionalExpressionEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConditionalExpressionEnd(cloneProperties(),firstToken,lastToken);
  }
  public AssignmentExpression getAssignmentExpression() {
    return ((PropertyOne<AssignmentExpression>)getProperty("assignmentExpression")).getValue();
  }
  public AssignmentExpression getAssignmentExpression1() {
    return ((PropertyOne<AssignmentExpression>)getProperty("assignmentExpression1")).getValue();
  }
}
