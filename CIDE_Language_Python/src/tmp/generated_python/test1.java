package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class test1 extends test {
  public test1(lambdef lambdef, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<lambdef>("lambdef", lambdef)
    }, firstToken, lastToken);
  }
  public test1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new test1(cloneProperties(),firstToken,lastToken);
  }
  public lambdef getLambdef() {
    return ((PropertyOne<lambdef>)getProperty("lambdef")).getValue();
  }
}
