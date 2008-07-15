package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Expression extends GenASTNode {
  public Expression(AssignmentExpression assignmentExpression, ArrayList<AssignmentExpression> assignmentExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AssignmentExpression>("assignmentExpression", assignmentExpression),
      new PropertyZeroOrMore<AssignmentExpression>("assignmentExpression1", assignmentExpression1)
    }, firstToken, lastToken);
  }
  public Expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Expression(cloneProperties(),firstToken,lastToken);
  }
  public AssignmentExpression getAssignmentExpression() {
    return ((PropertyOne<AssignmentExpression>)getProperty("assignmentExpression")).getValue();
  }
  public ArrayList<AssignmentExpression> getAssignmentExpression1() {
    return ((PropertyZeroOrMore<AssignmentExpression>)getProperty("assignmentExpression1")).getValue();
  }
}
