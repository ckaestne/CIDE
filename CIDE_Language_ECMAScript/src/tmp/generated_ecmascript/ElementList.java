package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ElementList extends GenASTNode {
  public ElementList(Elision elision, AssignmentExpression assignmentExpression, ArrayList<ElementListEnd> elementListEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Elision>("elision", elision),
      new PropertyOne<AssignmentExpression>("assignmentExpression", assignmentExpression),
      new PropertyZeroOrMore<ElementListEnd>("elementListEnd", elementListEnd)
    }, firstToken, lastToken);
  }
  public ElementList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ElementList(cloneProperties(),firstToken,lastToken);
  }
  public Elision getElision() {
    return ((PropertyZeroOrOne<Elision>)getProperty("elision")).getValue();
  }
  public AssignmentExpression getAssignmentExpression() {
    return ((PropertyOne<AssignmentExpression>)getProperty("assignmentExpression")).getValue();
  }
  public ArrayList<ElementListEnd> getElementListEnd() {
    return ((PropertyZeroOrMore<ElementListEnd>)getProperty("elementListEnd")).getValue();
  }
}
