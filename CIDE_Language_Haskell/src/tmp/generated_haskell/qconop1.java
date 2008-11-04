package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class qconop1 extends qconop {
  public qconop1(qconid qconid, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qconid>("qconid", qconid)
    }, firstToken, lastToken);
  }
  public qconop1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new qconop1(cloneProperties(),firstToken,lastToken);
  }
  public qconid getQconid() {
    return ((PropertyOne<qconid>)getProperty("qconid")).getValue();
  }
}
