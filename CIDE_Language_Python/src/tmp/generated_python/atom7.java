package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atom7 extends atom {
  public atom7(Number number, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Number>("number", number)
    }, firstToken, lastToken);
  }
  public atom7(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atom7(cloneProperties(),firstToken,lastToken);
  }
  public Number getNumber() {
    return ((PropertyOne<Number>)getProperty("number")).getValue();
  }
}
