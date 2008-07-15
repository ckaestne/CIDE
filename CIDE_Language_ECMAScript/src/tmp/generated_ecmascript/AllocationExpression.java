package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AllocationExpression extends GenASTNode {
  public AllocationExpression(MemberExpression memberExpression, ArrayList<AllocationExpressionEnd> allocationExpressionEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberExpression>("memberExpression", memberExpression),
      new PropertyZeroOrMore<AllocationExpressionEnd>("allocationExpressionEnd", allocationExpressionEnd)
    }, firstToken, lastToken);
  }
  public AllocationExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AllocationExpression(cloneProperties(),firstToken,lastToken);
  }
  public MemberExpression getMemberExpression() {
    return ((PropertyOne<MemberExpression>)getProperty("memberExpression")).getValue();
  }
  public ArrayList<AllocationExpressionEnd> getAllocationExpressionEnd() {
    return ((PropertyZeroOrMore<AllocationExpressionEnd>)getProperty("allocationExpressionEnd")).getValue();
  }
}
