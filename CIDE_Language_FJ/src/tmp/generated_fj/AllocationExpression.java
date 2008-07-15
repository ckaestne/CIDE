package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AllocationExpression extends GenASTNode {
  public AllocationExpression(ASTStringNode identifier, ExpressionList expressionList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<ExpressionList>("expressionList", expressionList)
    }, firstToken, lastToken);
  }
  public AllocationExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AllocationExpression(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ExpressionList getExpressionList() {
    return ((PropertyZeroOrOne<ExpressionList>)getProperty("expressionList")).getValue();
  }
}
