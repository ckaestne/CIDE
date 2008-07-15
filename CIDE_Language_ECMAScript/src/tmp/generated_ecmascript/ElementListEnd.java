package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ElementListEnd extends GenASTNode {
  public ElementListEnd(Elision elision, AssignmentExpression assignmentExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Elision>("elision", elision),
      new PropertyOne<AssignmentExpression>("assignmentExpression", assignmentExpression)
    }, firstToken, lastToken);
  }
  public ElementListEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ElementListEnd(cloneProperties(),firstToken,lastToken);
  }
  public Elision getElision() {
    return ((PropertyOne<Elision>)getProperty("elision")).getValue();
  }
  public AssignmentExpression getAssignmentExpression() {
    return ((PropertyOne<AssignmentExpression>)getProperty("assignmentExpression")).getValue();
  }
}
