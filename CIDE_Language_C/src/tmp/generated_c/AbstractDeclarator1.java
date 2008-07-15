package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AbstractDeclarator1 extends AbstractDeclarator {
  public AbstractDeclarator1(Pointer pointer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Pointer>("pointer", pointer)
    }, firstToken, lastToken);
  }
  public AbstractDeclarator1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AbstractDeclarator1(cloneProperties(),firstToken,lastToken);
  }
  public Pointer getPointer() {
    return ((PropertyOne<Pointer>)getProperty("pointer")).getValue();
  }
}
