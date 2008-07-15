package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentExpression2 extends AssignmentExpression {
  public AssignmentExpression2(ConditionalExpression conditionalExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ConditionalExpression>("conditionalExpression", conditionalExpression)
    }, firstToken, lastToken);
  }
  public AssignmentExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentExpression2(cloneProperties(),firstToken,lastToken);
  }
  public ConditionalExpression getConditionalExpression() {
    return ((PropertyOne<ConditionalExpression>)getProperty("conditionalExpression")).getValue();
  }
}
