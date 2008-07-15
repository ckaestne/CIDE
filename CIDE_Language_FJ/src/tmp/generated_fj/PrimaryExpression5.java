package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryExpression5 extends PrimaryExpression {
  public PrimaryExpression5(AllocationExpression allocationExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AllocationExpression>("allocationExpression", allocationExpression)
    }, firstToken, lastToken);
  }
  public PrimaryExpression5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryExpression5(cloneProperties(),firstToken,lastToken);
  }
  public AllocationExpression getAllocationExpression() {
    return ((PropertyOne<AllocationExpression>)getProperty("allocationExpression")).getValue();
  }
}
