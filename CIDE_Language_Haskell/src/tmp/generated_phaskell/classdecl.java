package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class classdecl extends topdecl {
  public classdecl(optContext optContext2, conid conid, tyvar tyvar, cdecls cdecls, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<optContext>("optContext2", optContext2),
      new PropertyOne<conid>("conid", conid),
      new PropertyOne<tyvar>("tyvar", tyvar),
      new PropertyZeroOrOne<cdecls>("cdecls", cdecls)
    }, firstToken, lastToken);
  }
  public classdecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new classdecl(cloneProperties(),firstToken,lastToken);
  }
  public optContext getOptContext2() {
    return ((PropertyOne<optContext>)getProperty("optContext2")).getValue();
  }
  public conid getConid() {
    return ((PropertyOne<conid>)getProperty("conid")).getValue();
  }
  public tyvar getTyvar() {
    return ((PropertyOne<tyvar>)getProperty("tyvar")).getValue();
  }
  public cdecls getCdecls() {
    return ((PropertyZeroOrOne<cdecls>)getProperty("cdecls")).getValue();
  }
}
