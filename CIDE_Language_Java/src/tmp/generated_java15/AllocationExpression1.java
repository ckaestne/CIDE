package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AllocationExpression1 extends AllocationExpression {
  public AllocationExpression1(PrimitiveType primitiveType, ArrayDimsAndInits arrayDimsAndInits, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimitiveType>("primitiveType", primitiveType),
      new PropertyOne<ArrayDimsAndInits>("arrayDimsAndInits", arrayDimsAndInits)
    }, firstToken, lastToken);
  }
  public AllocationExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AllocationExpression1(cloneProperties(),firstToken,lastToken);
  }
  public PrimitiveType getPrimitiveType() {
    return ((PropertyOne<PrimitiveType>)getProperty("primitiveType")).getValue();
  }
  public ArrayDimsAndInits getArrayDimsAndInits() {
    return ((PropertyOne<ArrayDimsAndInits>)getProperty("arrayDimsAndInits")).getValue();
  }
}
