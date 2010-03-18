package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CastLookahead1 extends CastLookahead {
  public CastLookahead1(PrimitiveType primitiveType, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimitiveType>("primitiveType", primitiveType)
    }, firstToken, lastToken);
  }
  public CastLookahead1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CastLookahead1(cloneProperties(),firstToken,lastToken);
  }
  public PrimitiveType getPrimitiveType() {
    return ((PropertyOne<PrimitiveType>)getProperty("primitiveType")).getValue();
  }
}
