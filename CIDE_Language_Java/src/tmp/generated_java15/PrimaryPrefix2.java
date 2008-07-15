package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryPrefix2 extends PrimaryPrefix {
  public PrimaryPrefix2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public PrimaryPrefix2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryPrefix2(cloneProperties(),firstToken,lastToken);
  }
}
