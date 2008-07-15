package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeArgument1 extends TypeArgument {
  public TypeArgument1(ReferenceTypeP referenceTypeP, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ReferenceTypeP>("referenceTypeP", referenceTypeP)
    }, firstToken, lastToken);
  }
  public TypeArgument1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeArgument1(cloneProperties(),firstToken,lastToken);
  }
  public ReferenceTypeP getReferenceTypeP() {
    return ((PropertyOne<ReferenceTypeP>)getProperty("referenceTypeP")).getValue();
  }
}
