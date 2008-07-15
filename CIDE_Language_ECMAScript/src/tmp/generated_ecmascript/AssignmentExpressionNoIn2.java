package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentExpressionNoIn2 extends AssignmentExpressionNoIn {
  public AssignmentExpressionNoIn2(ConditionalExpressionNoIn conditionalExpressionNoIn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ConditionalExpressionNoIn>("conditionalExpressionNoIn", conditionalExpressionNoIn)
    }, firstToken, lastToken);
  }
  public AssignmentExpressionNoIn2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentExpressionNoIn2(cloneProperties(),firstToken,lastToken);
  }
  public ConditionalExpressionNoIn getConditionalExpressionNoIn() {
    return ((PropertyOne<ConditionalExpressionNoIn>)getProperty("conditionalExpressionNoIn")).getValue();
  }
}
