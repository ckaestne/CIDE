package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class typeSignature extends decl {
  public typeSignature(signdecl signdecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<signdecl>("signdecl", signdecl)
    }, firstToken, lastToken);
  }
  public typeSignature(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new typeSignature(cloneProperties(),firstToken,lastToken);
  }
  public signdecl getSigndecl() {
    return ((PropertyOne<signdecl>)getProperty("signdecl")).getValue();
  }
}
