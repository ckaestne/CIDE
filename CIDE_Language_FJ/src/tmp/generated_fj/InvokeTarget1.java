package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class InvokeTarget1 extends InvokeTarget {
  public InvokeTarget1(AllocationExpression allocationExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AllocationExpression>("allocationExpression", allocationExpression)
    }, firstToken, lastToken);
  }
  public InvokeTarget1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new InvokeTarget1(cloneProperties(),firstToken,lastToken);
  }
  public AllocationExpression getAllocationExpression() {
    return ((PropertyOne<AllocationExpression>)getProperty("allocationExpression")).getValue();
  }
}
