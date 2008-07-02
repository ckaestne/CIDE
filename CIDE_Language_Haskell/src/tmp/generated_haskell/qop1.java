package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qop1 extends qop {
  public qop1(qvarop qvarop, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qvarop>("qvarop", qvarop)
    }, firstToken, lastToken);
  }
  public qop1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qop1(cloneProperties(),firstToken,lastToken);
  }
  public qvarop getQvarop() {
    return ((PropertyOne<qvarop>)getProperty("qvarop")).getValue();
  }
}
