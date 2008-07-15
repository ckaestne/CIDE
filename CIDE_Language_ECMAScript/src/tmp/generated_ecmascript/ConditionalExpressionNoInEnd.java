package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ConditionalExpressionNoInEnd extends GenASTNode {
  public ConditionalExpressionNoInEnd(AssignmentExpression assignmentExpression, AssignmentExpressionNoIn assignmentExpressionNoIn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AssignmentExpression>("assignmentExpression", assignmentExpression),
      new PropertyOne<AssignmentExpressionNoIn>("assignmentExpressionNoIn", assignmentExpressionNoIn)
    }, firstToken, lastToken);
  }
  public ConditionalExpressionNoInEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ConditionalExpressionNoInEnd(cloneProperties(),firstToken,lastToken);
  }
  public AssignmentExpression getAssignmentExpression() {
    return ((PropertyOne<AssignmentExpression>)getProperty("assignmentExpression")).getValue();
  }
  public AssignmentExpressionNoIn getAssignmentExpressionNoIn() {
    return ((PropertyOne<AssignmentExpressionNoIn>)getProperty("assignmentExpressionNoIn")).getValue();
  }
}
