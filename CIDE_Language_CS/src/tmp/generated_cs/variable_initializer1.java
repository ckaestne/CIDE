package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class variable_initializer1 extends variable_initializer {
  public variable_initializer1(array_initializer array_initializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<array_initializer>("array_initializer", array_initializer)
    }, firstToken, lastToken);
  }
  public variable_initializer1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new variable_initializer1(cloneProperties(),firstToken,lastToken);
  }
  public array_initializer getArray_initializer() {
    return ((PropertyOne<array_initializer>)getProperty("array_initializer")).getValue();
  }
}
