package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class export1 extends export {
  public export1(qvar qvar, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qvar>("qvar", qvar)
    }, firstToken, lastToken);
  }
  public export1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new export1(cloneProperties(),firstToken,lastToken);
  }
  public qvar getQvar() {
    return ((PropertyOne<qvar>)getProperty("qvar")).getValue();
  }
}
