package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class overloadable_binary_operator4 extends overloadable_binary_operator {
  public overloadable_binary_operator4(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public overloadable_binary_operator4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new overloadable_binary_operator4(cloneProperties(),firstToken,lastToken);
  }
}
