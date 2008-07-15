package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Type2 extends Type {
  public Type2(PrimitiveType primitiveType, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimitiveType>("primitiveType", primitiveType)
    }, firstToken, lastToken);
  }
  public Type2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Type2(cloneProperties(),firstToken,lastToken);
  }
  public PrimitiveType getPrimitiveType() {
    return ((PropertyOne<PrimitiveType>)getProperty("primitiveType")).getValue();
  }
}
