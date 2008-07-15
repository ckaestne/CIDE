package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MethodInvoke extends GenASTNode {
  public MethodInvoke(InvokeTarget invokeTarget, ASTStringNode identifier, ExpressionList expressionList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<InvokeTarget>("invokeTarget", invokeTarget),
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<ExpressionList>("expressionList", expressionList)
    }, firstToken, lastToken);
  }
  public MethodInvoke(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MethodInvoke(cloneProperties(),firstToken,lastToken);
  }
  public InvokeTarget getInvokeTarget() {
    return ((PropertyOne<InvokeTarget>)getProperty("invokeTarget")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ExpressionList getExpressionList() {
    return ((PropertyZeroOrOne<ExpressionList>)getProperty("expressionList")).getValue();
  }
}
