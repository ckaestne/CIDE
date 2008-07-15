package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class instancedecl extends topdecl {
  public instancedecl(optContext optContext3, qconid qconid, inst inst, block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<optContext>("optContext3", optContext3),
      new PropertyOne<qconid>("qconid", qconid),
      new PropertyOne<inst>("inst", inst),
      new PropertyZeroOrOne<block>("block", block)
    }, firstToken, lastToken);
  }
  public instancedecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new instancedecl(cloneProperties(),firstToken,lastToken);
  }
  public optContext getOptContext3() {
    return ((PropertyOne<optContext>)getProperty("optContext3")).getValue();
  }
  public qconid getQconid() {
    return ((PropertyOne<qconid>)getProperty("qconid")).getValue();
  }
  public inst getInst() {
    return ((PropertyOne<inst>)getProperty("inst")).getValue();
  }
  public block getBlock() {
    return ((PropertyZeroOrOne<block>)getProperty("block")).getValue();
  }
}
