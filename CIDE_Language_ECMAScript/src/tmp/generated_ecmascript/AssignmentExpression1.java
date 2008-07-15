package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentExpression1 extends AssignmentExpression {
  public AssignmentExpression1(LeftHandSideExpression leftHandSideExpression, AssignmentOperator assignmentOperator, AssignmentExpression assignmentExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LeftHandSideExpression>("leftHandSideExpression", leftHandSideExpression),
      new PropertyOne<AssignmentOperator>("assignmentOperator", assignmentOperator),
      new PropertyOne<AssignmentExpression>("assignmentExpression", assignmentExpression)
    }, firstToken, lastToken);
  }
  public AssignmentExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentExpression1(cloneProperties(),firstToken,lastToken);
  }
  public LeftHandSideExpression getLeftHandSideExpression() {
    return ((PropertyOne<LeftHandSideExpression>)getProperty("leftHandSideExpression")).getValue();
  }
  public AssignmentOperator getAssignmentOperator() {
    return ((PropertyOne<AssignmentOperator>)getProperty("assignmentOperator")).getValue();
  }
  public AssignmentExpression getAssignmentExpression() {
    return ((PropertyOne<AssignmentExpression>)getProperty("assignmentExpression")).getValue();
  }
}
