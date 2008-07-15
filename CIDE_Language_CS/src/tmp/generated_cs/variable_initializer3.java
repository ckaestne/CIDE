package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class variable_initializer3 extends variable_initializer {
  public variable_initializer3(stackalloc_initializer stackalloc_initializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<stackalloc_initializer>("stackalloc_initializer", stackalloc_initializer)
    }, firstToken, lastToken);
  }
  public variable_initializer3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new variable_initializer3(cloneProperties(),firstToken,lastToken);
  }
  public stackalloc_initializer getStackalloc_initializer() {
    return ((PropertyOne<stackalloc_initializer>)getProperty("stackalloc_initializer")).getValue();
  }
}
