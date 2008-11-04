package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class body2 extends body {
  public body2(topdecls topdecls1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<topdecls>("topdecls1", topdecls1)
    }, firstToken, lastToken);
  }
  public body2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new body2(cloneProperties(),firstToken,lastToken);
  }
  public topdecls getTopdecls1() {
    return ((PropertyOne<topdecls>)getProperty("topdecls1")).getValue();
  }
}
