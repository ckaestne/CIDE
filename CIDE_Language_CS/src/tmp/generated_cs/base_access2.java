package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class base_access2 extends base_access {
  public base_access2(element_access element_access, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<element_access>("element_access", element_access)
    }, firstToken, lastToken);
  }
  public base_access2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new base_access2(cloneProperties(),firstToken,lastToken);
  }
  public element_access getElement_access() {
    return ((PropertyOne<element_access>)getProperty("element_access")).getValue();
  }
}
