package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberExpression2 extends MemberExpression {
  public MemberExpression2(AllocationExpression allocationExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AllocationExpression>("allocationExpression", allocationExpression)
    }, firstToken, lastToken);
  }
  public MemberExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberExpression2(cloneProperties(),firstToken,lastToken);
  }
  public AllocationExpression getAllocationExpression() {
    return ((PropertyOne<AllocationExpression>)getProperty("allocationExpression")).getValue();
  }
}
