package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class newtypedecl extends definition {
  public newtypedecl(context context1, simpletype simpletype2, naam naam, newtypeParam newtypeParam, deriving deriving1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<context>("context1", context1),
      new PropertyOne<simpletype>("simpletype2", simpletype2),
      new PropertyOne<naam>("naam", naam),
      new PropertyOne<newtypeParam>("newtypeParam", newtypeParam),
      new PropertyZeroOrOne<deriving>("deriving1", deriving1)
    }, firstToken, lastToken);
  }
  public newtypedecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new newtypedecl(cloneProperties(),firstToken,lastToken);
  }
  public context getContext1() {
    return ((PropertyZeroOrOne<context>)getProperty("context1")).getValue();
  }
  public simpletype getSimpletype2() {
    return ((PropertyOne<simpletype>)getProperty("simpletype2")).getValue();
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
  public newtypeParam getNewtypeParam() {
    return ((PropertyOne<newtypeParam>)getProperty("newtypeParam")).getValue();
  }
  public deriving getDeriving1() {
    return ((PropertyZeroOrOne<deriving>)getProperty("deriving1")).getValue();
  }
}
