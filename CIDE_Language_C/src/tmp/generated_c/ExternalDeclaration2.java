package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExternalDeclaration2 extends ExternalDeclaration {
  public ExternalDeclaration2(Declaration declaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Declaration>("declaration", declaration)
    }, firstToken, lastToken);
  }
  public ExternalDeclaration2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExternalDeclaration2(cloneProperties(),firstToken,lastToken);
  }
  public Declaration getDeclaration() {
    return ((PropertyOne<Declaration>)getProperty("declaration")).getValue();
  }
}
