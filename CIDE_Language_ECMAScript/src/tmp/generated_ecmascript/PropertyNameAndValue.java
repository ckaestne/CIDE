package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PropertyNameAndValue extends GenASTNode {
  public PropertyNameAndValue(PropertyName propertyName, AssignmentExpression assignmentExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PropertyName>("propertyName", propertyName),
      new PropertyOne<AssignmentExpression>("assignmentExpression", assignmentExpression)
    }, firstToken, lastToken);
  }
  public PropertyNameAndValue(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PropertyNameAndValue(cloneProperties(),firstToken,lastToken);
  }
  public PropertyName getPropertyName() {
    return ((PropertyOne<PropertyName>)getProperty("propertyName")).getValue();
  }
  public AssignmentExpression getAssignmentExpression() {
    return ((PropertyOne<AssignmentExpression>)getProperty("assignmentExpression")).getValue();
  }
}
