package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExpressionNoIn extends GenASTNode {
  public ExpressionNoIn(AssignmentExpressionNoIn assignmentExpressionNoIn, ArrayList<AssignmentExpressionNoIn> assignmentExpressionNoIn1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AssignmentExpressionNoIn>("assignmentExpressionNoIn", assignmentExpressionNoIn),
      new PropertyZeroOrMore<AssignmentExpressionNoIn>("assignmentExpressionNoIn1", assignmentExpressionNoIn1)
    }, firstToken, lastToken);
  }
  public ExpressionNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExpressionNoIn(cloneProperties(),firstToken,lastToken);
  }
  public AssignmentExpressionNoIn getAssignmentExpressionNoIn() {
    return ((PropertyOne<AssignmentExpressionNoIn>)getProperty("assignmentExpressionNoIn")).getValue();
  }
  public ArrayList<AssignmentExpressionNoIn> getAssignmentExpressionNoIn1() {
    return ((PropertyZeroOrMore<AssignmentExpressionNoIn>)getProperty("assignmentExpressionNoIn1")).getValue();
  }
}
