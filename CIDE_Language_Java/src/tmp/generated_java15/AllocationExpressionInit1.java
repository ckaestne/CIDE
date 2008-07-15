package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AllocationExpressionInit1 extends AllocationExpressionInit {
  public AllocationExpressionInit1(ArrayDimsAndInits arrayDimsAndInits, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ArrayDimsAndInits>("arrayDimsAndInits", arrayDimsAndInits)
    }, firstToken, lastToken);
  }
  public AllocationExpressionInit1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AllocationExpressionInit1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayDimsAndInits getArrayDimsAndInits() {
    return ((PropertyOne<ArrayDimsAndInits>)getProperty("arrayDimsAndInits")).getValue();
  }
}
