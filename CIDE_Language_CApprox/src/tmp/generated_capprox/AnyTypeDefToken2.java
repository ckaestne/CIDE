package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyTypeDefToken2 extends AnyTypeDefToken {
  public AnyTypeDefToken2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public AnyTypeDefToken2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyTypeDefToken2(cloneProperties(),firstToken,lastToken);
  }
}
