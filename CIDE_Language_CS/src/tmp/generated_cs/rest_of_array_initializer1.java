package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class rest_of_array_initializer1 extends rest_of_array_initializer {
  public rest_of_array_initializer1(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public rest_of_array_initializer1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new rest_of_array_initializer1(cloneProperties(),firstToken,lastToken);
  }
}
