package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class slice extends GenASTNode {
  public slice(ct ct, ct ct1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ct>("ct", ct),
      new PropertyZeroOrOne<ct>("ct1", ct1)
    }, firstToken, lastToken);
  }
  public slice(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new slice(cloneProperties(),firstToken,lastToken);
  }
  public ct getCt() {
    return ((PropertyOne<ct>)getProperty("ct")).getValue();
  }
  public ct getCt1() {
    return ((PropertyZeroOrOne<ct>)getProperty("ct1")).getValue();
  }
}
