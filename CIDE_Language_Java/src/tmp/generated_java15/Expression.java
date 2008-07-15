package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Expression extends GenASTNode {
  public Expression(ConditionalExpression conditionalExpression, AssignExp assignExp, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ConditionalExpression>("conditionalExpression", conditionalExpression),
      new PropertyZeroOrOne<AssignExp>("assignExp", assignExp)
    }, firstToken, lastToken);
  }
  public Expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Expression(cloneProperties(),firstToken,lastToken);
  }
  public ConditionalExpression getConditionalExpression() {
    return ((PropertyOne<ConditionalExpression>)getProperty("conditionalExpression")).getValue();
  }
  public AssignExp getAssignExp() {
    return ((PropertyZeroOrOne<AssignExp>)getProperty("assignExp")).getValue();
  }
}
