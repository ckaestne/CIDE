package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class WildcardBounds2 extends WildcardBounds {
  public WildcardBounds2(ReferenceTypeP referenceTypeP1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ReferenceTypeP>("referenceTypeP1", referenceTypeP1)
    }, firstToken, lastToken);
  }
  public WildcardBounds2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new WildcardBounds2(cloneProperties(),firstToken,lastToken);
  }
  public ReferenceTypeP getReferenceTypeP1() {
    return ((PropertyOne<ReferenceTypeP>)getProperty("referenceTypeP1")).getValue();
  }
}
