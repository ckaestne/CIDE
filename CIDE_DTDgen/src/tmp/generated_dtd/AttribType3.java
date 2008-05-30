package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AttribType3 extends AttribType {
  public AttribType3(EnumeratedType enumeratedType, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<EnumeratedType>("enumeratedType", enumeratedType)
    }, firstToken, lastToken);
  }
  public AttribType3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new AttribType3(cloneProperties(),firstToken,lastToken);
  }
  public EnumeratedType getEnumeratedType() {
    return ((PropertyOne<EnumeratedType>)getProperty("enumeratedType")).getValue();
  }
}
