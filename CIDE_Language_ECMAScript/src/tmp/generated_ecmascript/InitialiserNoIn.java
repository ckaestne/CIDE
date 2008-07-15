package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class InitialiserNoIn extends GenASTNode {
  public InitialiserNoIn(AssignmentExpressionNoIn assignmentExpressionNoIn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AssignmentExpressionNoIn>("assignmentExpressionNoIn", assignmentExpressionNoIn)
    }, firstToken, lastToken);
  }
  public InitialiserNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new InitialiserNoIn(cloneProperties(),firstToken,lastToken);
  }
  public AssignmentExpressionNoIn getAssignmentExpressionNoIn() {
    return ((PropertyOne<AssignmentExpressionNoIn>)getProperty("assignmentExpressionNoIn")).getValue();
  }
}
