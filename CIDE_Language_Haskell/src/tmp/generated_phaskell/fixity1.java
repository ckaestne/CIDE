package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fixity1 extends fixity {
  public fixity1(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public fixity1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fixity1(cloneProperties(),firstToken,lastToken);
  }
}
