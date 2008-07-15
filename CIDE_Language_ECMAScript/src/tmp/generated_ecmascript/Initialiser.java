package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Initialiser extends GenASTNode {
  public Initialiser(AssignmentExpression assignmentExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AssignmentExpression>("assignmentExpression", assignmentExpression)
    }, firstToken, lastToken);
  }
  public Initialiser(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Initialiser(cloneProperties(),firstToken,lastToken);
  }
  public AssignmentExpression getAssignmentExpression() {
    return ((PropertyOne<AssignmentExpression>)getProperty("assignmentExpression")).getValue();
  }
}
