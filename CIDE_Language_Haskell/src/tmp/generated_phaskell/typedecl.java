package tmp.generated_phaskell;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gparser.Token;

public class typedecl extends topdecl {
  public typedecl(simpletype simpletype, declrhs declrhs, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<simpletype>("simpletype", simpletype),
      new PropertyOne<declrhs>("declrhs", declrhs)
    }, firstToken, lastToken);
  }
  public typedecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new typedecl(cloneProperties(),firstToken,lastToken);
  }
  public simpletype getSimpletype() {
    return ((PropertyOne<simpletype>)getProperty("simpletype")).getValue();
  }
  public declrhs getDeclrhs() {
    return ((PropertyOne<declrhs>)getProperty("declrhs")).getValue();
  }
}
