package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class UnaryExpressionSizeOf2 extends UnaryExpressionSizeOf {
  public UnaryExpressionSizeOf2(TypeName typeName, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeName>("typeName", typeName)
    }, firstToken, lastToken);
  }
  public UnaryExpressionSizeOf2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new UnaryExpressionSizeOf2(cloneProperties(),firstToken,lastToken);
  }
  public TypeName getTypeName() {
    return ((PropertyOne<TypeName>)getProperty("typeName")).getValue();
  }
}
