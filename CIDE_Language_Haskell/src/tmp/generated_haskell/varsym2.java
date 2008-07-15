package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varsym2 extends varsym {
  public varsym2(otherOperators otherOperators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<otherOperators>("otherOperators", otherOperators)
    }, firstToken, lastToken);
  }
  public varsym2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new varsym2(cloneProperties(),firstToken,lastToken);
  }
  public otherOperators getOtherOperators() {
    return ((PropertyOne<otherOperators>)getProperty("otherOperators")).getValue();
  }
}
