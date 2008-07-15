package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ArgumentExpressionList extends GenASTNode {
  public ArgumentExpressionList(AssignmentExpression assignmentExpression, ArrayList<AssignmentExpression> assignmentExpression1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AssignmentExpression>("assignmentExpression", assignmentExpression),
      new PropertyZeroOrMore<AssignmentExpression>("assignmentExpression1", assignmentExpression1)
    }, firstToken, lastToken);
  }
  public ArgumentExpressionList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ArgumentExpressionList(cloneProperties(),firstToken,lastToken);
  }
  public AssignmentExpression getAssignmentExpression() {
    return ((PropertyOne<AssignmentExpression>)getProperty("assignmentExpression")).getValue();
  }
  public ArrayList<AssignmentExpression> getAssignmentExpression1() {
    return ((PropertyZeroOrMore<AssignmentExpression>)getProperty("assignmentExpression1")).getValue();
  }
}
