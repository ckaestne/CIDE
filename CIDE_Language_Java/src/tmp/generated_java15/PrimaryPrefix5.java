package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryPrefix5 extends PrimaryPrefix {
  public PrimaryPrefix5(AllocationExpression allocationExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AllocationExpression>("allocationExpression", allocationExpression)
    }, firstToken, lastToken);
  }
  public PrimaryPrefix5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryPrefix5(cloneProperties(),firstToken,lastToken);
  }
  public AllocationExpression getAllocationExpression() {
    return ((PropertyOne<AllocationExpression>)getProperty("allocationExpression")).getValue();
  }
}
