package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DTDEntry1 extends DTDEntry {
  public DTDEntry1(ElementDecl elementDecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ElementDecl>("elementDecl", elementDecl)
    }, firstToken, lastToken);
  }
  public DTDEntry1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new DTDEntry1(cloneProperties(),firstToken,lastToken);
  }
  public ElementDecl getElementDecl() {
    return ((PropertyOne<ElementDecl>)getProperty("elementDecl")).getValue();
  }
}
