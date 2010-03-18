package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VariableInitializer1 extends VariableInitializer {
  public VariableInitializer1(ArrayInitializer arrayInitializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ArrayInitializer>("arrayInitializer", arrayInitializer)
    }, firstToken, lastToken);
  }
  public VariableInitializer1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VariableInitializer1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayInitializer getArrayInitializer() {
    return ((PropertyOne<ArrayInitializer>)getProperty("arrayInitializer")).getValue();
  }
}
