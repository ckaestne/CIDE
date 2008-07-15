package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class production4 extends production {
  public production4(bnf_production bnf_production, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<bnf_production>("bnf_production", bnf_production)
    }, firstToken, lastToken);
  }
  public production4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new production4(cloneProperties(),firstToken,lastToken);
  }
  public bnf_production getBnf_production() {
    return ((PropertyOne<bnf_production>)getProperty("bnf_production")).getValue();
  }
}
