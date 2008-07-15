package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentExpressionNoIn1 extends AssignmentExpressionNoIn {
  public AssignmentExpressionNoIn1(LeftHandSideExpression leftHandSideExpression, AssignmentOperator assignmentOperator, AssignmentExpressionNoIn assignmentExpressionNoIn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LeftHandSideExpression>("leftHandSideExpression", leftHandSideExpression),
      new PropertyOne<AssignmentOperator>("assignmentOperator", assignmentOperator),
      new PropertyOne<AssignmentExpressionNoIn>("assignmentExpressionNoIn", assignmentExpressionNoIn)
    }, firstToken, lastToken);
  }
  public AssignmentExpressionNoIn1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentExpressionNoIn1(cloneProperties(),firstToken,lastToken);
  }
  public LeftHandSideExpression getLeftHandSideExpression() {
    return ((PropertyOne<LeftHandSideExpression>)getProperty("leftHandSideExpression")).getValue();
  }
  public AssignmentOperator getAssignmentOperator() {
    return ((PropertyOne<AssignmentOperator>)getProperty("assignmentOperator")).getValue();
  }
  public AssignmentExpressionNoIn getAssignmentExpressionNoIn() {
    return ((PropertyOne<AssignmentExpressionNoIn>)getProperty("assignmentExpressionNoIn")).getValue();
  }
}
