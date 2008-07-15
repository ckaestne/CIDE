package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class modid extends GenASTNode {
  public modid(qconid qconid, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<qconid>("qconid", qconid)
    }, firstToken, lastToken);
  }
  public modid(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new modid(cloneProperties(),firstToken,lastToken);
  }
  public qconid getQconid() {
    return ((PropertyOne<qconid>)getProperty("qconid")).getValue();
  }
}
