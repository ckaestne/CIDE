package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class newtypedecl extends topdecl {
  public newtypedecl(optContext optContext1, simpletype simpletype2, declrhs declrhs1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<optContext>("optContext1", optContext1),
      new PropertyOne<simpletype>("simpletype2", simpletype2),
      new PropertyOne<declrhs>("declrhs1", declrhs1)
    }, firstToken, lastToken);
  }
  public newtypedecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new newtypedecl(cloneProperties(),firstToken,lastToken);
  }
  public optContext getOptContext1() {
    return ((PropertyOne<optContext>)getProperty("optContext1")).getValue();
  }
  public simpletype getSimpletype2() {
    return ((PropertyOne<simpletype>)getProperty("simpletype2")).getValue();
  }
  public declrhs getDeclrhs1() {
    return ((PropertyOne<declrhs>)getProperty("declrhs1")).getValue();
  }
}
