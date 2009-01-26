package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atom4 extends atom {
  public atom4(dictmaker dictmaker, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<dictmaker>("dictmaker", dictmaker)
    }, firstToken, lastToken);
  }
  public atom4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atom4(cloneProperties(),firstToken,lastToken);
  }
  public dictmaker getDictmaker() {
    return ((PropertyZeroOrOne<dictmaker>)getProperty("dictmaker")).getValue();
  }
}
