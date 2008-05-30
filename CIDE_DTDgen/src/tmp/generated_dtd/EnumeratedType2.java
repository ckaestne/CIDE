package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumeratedType2 extends EnumeratedType {
  public EnumeratedType2(Enumeration enumeration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Enumeration>("enumeration", enumeration)
    }, firstToken, lastToken);
  }
  public EnumeratedType2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new EnumeratedType2(cloneProperties(),firstToken,lastToken);
  }
  public Enumeration getEnumeration() {
    return ((PropertyOne<Enumeration>)getProperty("enumeration")).getValue();
  }
}
