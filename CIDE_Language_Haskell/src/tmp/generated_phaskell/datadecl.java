package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class datadecl extends topdecl {
  public datadecl(optContext optContext, simpletype simpletype1, constrs constrs, deriving deriving, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<optContext>("optContext", optContext),
      new PropertyOne<simpletype>("simpletype1", simpletype1),
      new PropertyOne<constrs>("constrs", constrs),
      new PropertyZeroOrOne<deriving>("deriving", deriving)
    }, firstToken, lastToken);
  }
  public datadecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new datadecl(cloneProperties(),firstToken,lastToken);
  }
  public optContext getOptContext() {
    return ((PropertyOne<optContext>)getProperty("optContext")).getValue();
  }
  public simpletype getSimpletype1() {
    return ((PropertyOne<simpletype>)getProperty("simpletype1")).getValue();
  }
  public constrs getConstrs() {
    return ((PropertyOne<constrs>)getProperty("constrs")).getValue();
  }
  public deriving getDeriving() {
    return ((PropertyZeroOrOne<deriving>)getProperty("deriving")).getValue();
  }
}
