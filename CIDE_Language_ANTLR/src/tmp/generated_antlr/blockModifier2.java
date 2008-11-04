package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class blockModifier2 extends blockModifier {
  public blockModifier2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public blockModifier2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new blockModifier2(cloneProperties(),firstToken,lastToken);
  }
}
