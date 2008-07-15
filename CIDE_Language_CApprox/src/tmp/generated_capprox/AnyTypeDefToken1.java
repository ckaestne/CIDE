package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnyTypeDefToken1 extends AnyTypeDefToken {
  public AnyTypeDefToken1(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public AnyTypeDefToken1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnyTypeDefToken1(cloneProperties(),firstToken,lastToken);
  }
}
