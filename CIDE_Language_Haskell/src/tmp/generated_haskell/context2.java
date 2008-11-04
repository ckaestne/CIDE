package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class context2 extends context {
  public context2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public context2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new context2(cloneProperties(),firstToken,lastToken);
  }
}
