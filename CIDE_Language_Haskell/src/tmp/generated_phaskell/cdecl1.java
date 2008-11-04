package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class cdecl1 extends cdecl {
  public cdecl1(signdecl signdecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<signdecl>("signdecl", signdecl)
    }, firstToken, lastToken);
  }
  public cdecl1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new cdecl1(cloneProperties(),firstToken,lastToken);
  }
  public signdecl getSigndecl() {
    return ((PropertyOne<signdecl>)getProperty("signdecl")).getValue();
  }
}
