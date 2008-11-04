package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class newtypeParam2 extends newtypeParam {
  public newtypeParam2(type type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type)
    }, firstToken, lastToken);
  }
  public newtypeParam2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new newtypeParam2(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
}
