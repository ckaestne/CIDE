package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimarySuffix2 extends PrimarySuffix {
  public PrimarySuffix2(AllocationExpression allocationExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AllocationExpression>("allocationExpression", allocationExpression)
    }, firstToken, lastToken);
  }
  public PrimarySuffix2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimarySuffix2(cloneProperties(),firstToken,lastToken);
  }
  public AllocationExpression getAllocationExpression() {
    return ((PropertyOne<AllocationExpression>)getProperty("allocationExpression")).getValue();
  }
}
