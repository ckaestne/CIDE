package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qvar1 extends qvar {
  public qvar1(qvarid qvarid, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qvarid>("qvarid", qvarid)
    }, firstToken, lastToken);
  }
  public qvar1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qvar1(cloneProperties(),firstToken,lastToken);
  }
  public qvarid getQvarid() {
    return ((PropertyOne<qvarid>)getProperty("qvarid")).getValue();
  }
}
