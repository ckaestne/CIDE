package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryPrefix7 extends PrimaryPrefix {
  public PrimaryPrefix7(Name name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name)
    }, firstToken, lastToken);
  }
  public PrimaryPrefix7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryPrefix7(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
}
